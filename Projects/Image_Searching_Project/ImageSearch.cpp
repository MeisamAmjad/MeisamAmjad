/* 
 * ------------------------------------------
 * Copyright (C) 2017 amjadm@miamiOH.edu
 * 
 *       High Performance Computing
 *          Project - Homework #7
 * 
 * Brute force image searching approach that 
 * look for the mask image inside the main
 * image which both images are PNG files.
 * This class developed as OpenMp-parallelized
 * image searching.
 * 
 * File:   ImageSearch.cpp
 * 
 * Author: Meisam Amjad     amjadm@miamiOH.edu
 * ------------------------------------------
 */

#include "ImageSearch.h"
#include <omp.h>
#include <iostream>
#include <vector>
#include <algorithm>  // For using max

// ----------------------------------------------------------------
// ----------------------- PUBLIC Methods -------------------------
// ----------------------------------------------------------------

ImageSearch::ImageSearch(const str& largeImg, 
                         const str& srchImg, 
                         const str& outputPath, 
                         const bool& isMask, 
                         const int& matchPercent, 
                         const int& tolerance) : outputPath(outputPath),
                                                isMask(isMask),
                                                matchPercent(matchPercent),
                                                Tolerance(tolerance) {
    this->largeImg.load(largeImg);
    this->srchImg.load(srchImg);
}

void 
ImageSearch::search() {
    const int& maxRow  = largeImg.getHeight() - srchImg.getHeight();
    const int& maxCol  = largeImg.getWidth()  - srchImg.getWidth();
    const int& percent = srchImg.getWidth() * srchImg.getHeight() * 
                                                        matchPercent / 100;
    search(srchImg, largeImg, maxRow, maxCol, percent, Tolerance, 
                                                                result_indices);
}

void
ImageSearch::drawResults() {
    if (result_indices.size() != 0)  // Show result if there is any.
        drawResults(result_indices, outputPath);
    else                             // Display error message if there is not.
        std::cerr << "\nError! There is no result.\n"
                     "(hint):Run search method." << std::endl;
}

inline
Region::Region(int row, int col, int h, int w): 
                                row1(row), col1(col), height(h), width(w) {
    row2 = row + h;  // Calculating row2(row-> for the end point of region)
    col2 = col + w;  // Calculating col2(col-> for the end point of region)
}

inline
bool
Region::overlap(const Region& other) const {
    // Checks if other has overlap on the current region.
    return !((row1 > other.row2) || (col1 > other.col2) ||
             (row2 < other.row1) || (col2 < other.col1));
}

// ----------------------------------------------------------------
// ----------------------- PRIVATE Methods -------------------------
// ----------------------------------------------------------------

inline 
bool 
operator==(const auto& src, const pixel& other) {
    auto p_other = &other[0];  // Using pointer to being faster.
    return (*(src + 0) == *(p_other + 0) &&
            *(src + 1) == *(p_other + 1) &&
            *(src + 2) == *(p_other + 2));
}

inline
int 
operator-(const auto& src, const pixel& other) {
    auto p_other = &other[0];  // Using pointer to being faster.
    const int& R = std::abs(*(src + 0) - *(p_other + 0));  // Adds Reds
    const int& G = std::abs(*(src + 1) - *(p_other + 1));  // Adds Greens
    const int& B = std::abs(*(src + 2) - *(p_other + 2));  // Adds Blues
    return std::max(std::max(R, G), B);  // Returns the maximum difference  
}

inline
std::ostream& operator<<(std::ostream& out, const Region& rgn) {
         return out << "sub-image matched at: "
                    << rgn.row1 << ", " << rgn.col1 << ", "
                    << rgn.row2 << ", " << rgn.col2;
}

inline
auto
ImageSearch::getPixel(const PNG& img, const int& row, const int& col) const {
    const int& index = row * img.getWidth() * 4 + col * 4;  // produces index.
    return (&img.getBuffer()[0] + index);  // Returns a pointer from the index.
}

void 
ImageSearch::search(const PNG& mask, const PNG& img, 
                    const int& numRows, const int& numCols, 
                    const int& percentage, const int& tolerance, 
                    rgnList& matched) {
    const int& width = mask.getWidth(), 
              height = mask.getHeight();
    result temp_result(omp_get_max_threads());  // Temporary holding results.
    // Beginning of the parallelization using static scheduling on for loop.
    // Each thread works on different i (representing rows).
#pragma omp parallel for schedule(static)
    for (int i = 0; (i <= numRows); ++i) {
        const int thrID = omp_get_thread_num();  // Holds the thread number.
        for (int j = 0; (j <= numCols); ++j) {
            const Region& region = Region(i, j, height, width);
            const int& netMatch = countPixelMatches(mask, img, i, j, tolerance);
            if (netMatch > percentage) {
                temp_result[thrID].push_back(region);  // Adds region to vector.
                j = region.col2;  // jump to the next region.
            }
        }
    }
    // Merging results. Also check for intersection again for 2 reasons:
    // 1. The code above only jumps based on j(column) meaning there might 
    //    be some regions that have intersect because of their i(row).
    // 2. The other reason is since each thread worked on different regions,
    //    there might be an intersect between 2 regions from different threads.
    for (auto list = temp_result.begin(); (list != temp_result.end()); ++list)
       std::copy_if((*list).begin(), (*list).end(), std::back_inserter(matched)
                , [&](const Region& r) {return !hasIntersect(matched, r);});
}

int 
ImageSearch::countPixelMatches(const PNG& mask, const PNG& img, 
                               const int& row, const int& col, 
                               const int& tolerance) const {
    int sumMatched = 0;
    // Calculates average background of img based on given mask and row, col.
    const pixel& avgBg = getAvgBackground(mask, img, row, col);
    auto maskImg = &mask.getBuffer()[0];  // A pointer to the mask buffer.
    for (int i = 0 ; i < mask.getHeight(); ++i) {
        // Goes to the beginning of the row i.
        auto mainImg = getPixel(img, (i + row), col);
        for (int j = 0; j < mask.getWidth(); ++j, maskImg += 4, mainImg += 4)
            // Based on the avgBg or being BLACK, adds 1 or -1 to sumMatched.
            sumMatched += (maskImg == BLACK ? -1 : 1) * 
                          (mainImg - avgBg < tolerance ? -1 : 1);
    }
    return sumMatched;
}

pixel
ImageSearch::getAvgBackground(const PNG& mask, const PNG& img,
                              const int& row, const int& col) const {
    int R = 0, G = 0, B = 0, N = 0;
    auto maskImg = &mask.getBuffer()[0];  // A pointer to the mask buffer.
    for (int i = 0 ; i < mask.getHeight(); ++i) {
        // Goes to the beginning of the row i.
        auto mainImg = getPixel(img, (i + row), col);
        for (int j = 0; j < mask.getWidth(); ++j, maskImg += 4, mainImg += 4)
            if (maskImg == BLACK) {
                R += *(mainImg + 0);  // Adds all Reds.
                G += *(mainImg + 1);  // Adds all Greens.
                B += *(mainImg + 2);  // Adds all Blues.
                ++N;                  // Counts the number of black pixels.
            }
    }
    // Calculates average of each color and returns it as a pixel object.
    return {(uChar)(R / N), (uChar)(G / N), (uChar)(B / N)};
}

void
ImageSearch::drawResults(const rgnList& indices, const str& filePath) {
    int N = 0;             // For holding the number of results.
    PNG output(largeImg);  // Makes a new PNG object filled up with main-image.
    for (auto rg = indices.begin(); (rg != indices.end()); ++rg) {
        std::cout << (*rg) << std::endl;  // print region object on display.
        // Draws a red box around each region that has been found.
        drawBox(output, (*rg).row1, (*rg).col1, (*rg).width, (*rg).height);
        ++N;  // count number of result.
    }
    std::cout << "Number of matches: " << N << std::endl;  // Print on display
    output.write(filePath);  // Write in a file
}

void 
ImageSearch::drawBox(PNG& png, const int& row, const int& col, 
                       const int& width, const int& height) {
    // Draw horizontal lines
    for (int i = 0; (i < width); i++) {
        png.setRed(row, col + i);
        png.setRed(row + height, col + i); 
    }
    // Draw vertical lines
    for (int i = 0; (i < height); i++) { 
        png.setRed(row + i, col); 
        png.setRed(row + i, col + width);
    }
}

bool
ImageSearch::hasIntersect(const rgnList& matched, 
                          const Region& reg2) const {
    // Return true if there is an intersect between reg2 with any of regions.
    return (std::find_if(matched.begin(), matched.end(),
            [&](const Region& r){return r.overlap(reg2);}) != matched.end());
}

int
main(int argc, char* argv[]) {
    if ((argc < 4) || (argc > 7)) {  // Cheking for error in given parameters.
        std::cerr << " The number of parameters does not match!\n";
        return 1;
    }
    // str cmd = "export OMP_NUM_THREADS=8";
    // system(cmd.c_str());
    const char* check = "true";
    // Defining ImageSearch object based on the given parameters from cmd-line.
    ImageSearch img(argv[1], argv[2], argv[3],
                   (argc > 4 ? argv[4] == check : false),
                   (argc > 5 ? std::stoi(argv[5]) : 75),
                   (argc > 6 ? std::stoi(argv[6]) : 32));
    img.search();       // Starts searching for mask inside the main image.
    img.drawResults();  // Prints results and draws a PNG picture.
    return 0;
}
