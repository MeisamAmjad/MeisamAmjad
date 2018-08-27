#ifndef IMAGESEARCH_H
#define IMAGESEARCH_H

/* 
 *  ------------------------------------------
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
 * File:   ImageSearch.h
 * 
 * Author: Meisam Amjad     amjadm@miamiOH.edu
 * ------------------------------------------
 */

#include <png.h>
#include <string>
#include <vector>
#include "PNG.h"

/**
 * This class is designed for holding some details related to 
 * each triangular region which represents a specific region of a PNG picture
 * that is used for searching in. 
 * This class holds row, col for the start point of the region, and the end
 * of the region. Also, width and height of the region.
 */
class Region {
    friend inline 
    std::ostream& operator<<(std::ostream& out, const Region& rgn);
    
public:
    /** Defining fields. */
    int row1, col1, height, width, row2, col2; 
    
    /**
     * \brief Constructor.
     */
    inline Region(int row, int col, int h, int w);
    
    /**
     * Destruction.
     */
    ~Region() {}
    
    /**
     * \brief checks if the given Region object has any intersect with
     *        current Region.
     * \param other Region object.
     * \return true if there is any intersect between 2 Regions.
     *         false otherwise.
     */
    inline bool overlap(const Region& other) const;
};

using str     = std::string;           // Alias for std::string.
using uChar   = unsigned char;         // Alias for unsigned char.
using pixel   = std::vector<uChar>;    // Alias for std::vector<uChar>
using intVec  = std::vector<int>;      // Alias for std::vector<int>.
using rgnList = std::vector<Region>;   // Alias for std::vector<intVec>.
using result  = std::vector<rgnList>;  // Alias for std::vector<rgnList>. 

const pixel& BLACK = {0, 0, 0, 255};  // Defines a Black object.

/**
 * \brief This class designed to search for a mask image in another large scale 
 *        image in which both images should have PNG formate. This class can 
 *        find similar regions and report them by displaying only similar 
 *        regions and drawing a PNG file with having a red-box around founded
 *        regions.
 *  This class can find similarities based on the given tolerance and the given
 *  percentage. Tolerance is for 
 */
class ImageSearch {
    /**
     * \brief having the region class to be used in this class. 
     *
     */
    friend Region;
    
    /** \brief Overload operator == for comparing two pixels.
     *         The first parameter is a pointer to the pixel.
     *         The second parameter is a pixel object.
     *  \return true if all elements of two pixels are equal. 
     *          false otherwise.
     */
    friend inline bool operator==(const auto& src, const pixel& other);
    
    /**
     * \brief Overload operator - deducting all elements of two pixels and
     *        returns the maximum deduction number between all elements.
     *        The first parameter is a pointer to the pixel.
     *        The second parameter is a pixel object.
     * \return maximum number from deducting corresponding elements of two
     *         pixels.
     */
    friend inline int operator-(const auto& src, const pixel& other);
    
public:    
    /**
     * \brief Constructor.
     * 
     * \param largeImg path for the large image
     * 
     * \param srchImg path for the search image.
     * 
     * \param outputPath path for the output file.
     *        By default it would be empty.
     * \param isMask true or false.
     *        By default it would be true.        
     * \param matchPercent percentage of matching.
     *        By default it would be 75 %.
     * \param tolerance an error between pixel color values.
     *        By default it would be 32.
     */
    inline ImageSearch(const str& largeImg,
                       const str& srchImg, 
                       const str& outputPath = "",
                       const bool& isMask = true,
                       const int& matchPercent = 75,
                       const int& tolerance = 32);
    
    /**
     * Destruction.
     */
    ~ImageSearch() {}
    
    /**
     * \brief search for srch-Image inside the large-image.
     *        
     */
    void search();
    
    /**
     * \bried First, prints all results on the screen.
     *        Then, produces a PNG file and draw a red box around the regions
     *        the search method found already.
     * \param Optional. By default would be result founded by search method
     *        already.
     * \param Optional. By default would be the given file path that the class
     *        holds already for the output.
     */
    void drawResults();
                       
private:
    // ******************* Defining Fields *******************
    /*
     * Holds a PNG object that we use to search in.
     * Its value will be passed in as first command-line argument to
     * the program.
     */
    PNG largeImg;
    
    /*
     * Holds a PNG object that we use to search for, aka mask.
     * Its value will be passed in as second command-line argument to
     * the program.
     */
    PNG srchImg;
    
    /*
     * Holds the path for the output PNG file to which the resulting image
     * is to be written.
     * Its value will be passed in as third command-line 
     * argument to the program.
     */
    const str outputPath;
    
    /*
     * Holds true or false representing whether or not srch-image is mask.
     * By default it would be true.
     * Its value will be passed in as forth command-line argument to 
     * the program.
     */
    const bool isMask;
    
    /*
     * Holds the desired percentage pixel match to determine a match between
     * the given srch-image and the search image. 
     * By default it would be 75%.
     * Its value will be passed in as fifth command-line argument to 
     * the program.
     */
    const int matchPercent;
    
    /*
     * Holds the acceptable tolerance between pixel color values in 
     * the large image versus the search image.
     * By default it would be range of 32.
     * Its value will be passed in as sixth command-line argument to 
     * the program.
     */
    const int Tolerance;
    
    /*
     * Holds a list of all founded indices from main image.
     * Each indices represent the start point of specific region
     * which adding mask.Height() to row, and mask.Width() to col
     * we can have the full allocation of each region.
     */
    rgnList result_indices;
        
    // ------------------- End of Fields ----------------------
    // --------------------------------------------------------
    // *************** Defining Private METHODS ***************
    //
         
    /*
     * \brief given the PNG object, return a pointer to a pixel at the 
     *        given row and col. 
     * \return A pointer to the pixel in img object(row, col).
     */
    inline auto getPixel(const PNG& img, const int& row, const int& col) const;
    
    /*
     * \brief A helper method for search() method
     * 
     * \param mask search image object. By default would be srchImg.
     * \param img main image object. By default would be largeImg.
     * \param numRows difference between number of rows in 2 PNG object.
     * \param numCols difference between number of cols in 2 PNG object.
     * \param percentage percentage pixel match.
     * \param tolerance.
     * \param result_output a reference to the list of found indices that
     *        holds the output of this method.
     * \param \return result_output as a reference parameter.
     */
    void search(const PNG& mask , const PNG& img,
                const int& numRows, const int& numCols, 
                const int& percentage, const int& tolerance,
                rgnList& result_output);
    /*
     * \brief calculates average background color of the pixels 
     *        corresponding to the black pixels in the search-image.
     *        This method uses the given blackPixels which is a list
     *        of index related to black pixels.
     * \param srchImg PNG object which is image that it searches for.
     * \param img PNG object which is a main Image.
     * \param row row start index of largeImage's region for searching in.
     * \param col col start index of largeImage's region for searching in.
     * \return A pixel that contains average background of img corresponding
     *         to the black pixels in search-image.
     */
    pixel getAvgBackground(const PNG& mask, const PNG& img,
                           const int& row, const int& col) const;
    
    /*
     * \brief Counts how many pixels in srchImg matches with img
     *        and returns it.
     * \param srchImg PNG object which is used to search for.
     * \param mask PNG object which is a main Image for searching in.
     * \param row row start index of largeImage's region for searching in.
     * \param col col start index of largeImage's region for searching in.
     * \param tolerance. tolerance for comparing average backgrounds.
     * \return A pixel that contains average background of img corresponding
     *         to the black pixels in search-image.
     */
    int countPixelMatches(const PNG& mask, const PNG& img,
                          const int& row, const int& col, 
                          const int& tolerance) const;
    
    /*
     * \brief Checks if the given row, col has any intersect with any matched
     *        regions.
     * \return true if it can find intersect, false otherwise.
     */
    bool hasIntersect(const rgnList& matched, const Region& reg2) const;
    
    /*
     * \brief a Helper method for drawResults() method.
     * \param results that already be found by search() method.
     * \param The file path that the class holds already for the output.
     */
    void drawResults(const rgnList& indices, const str& filePath);
    
    /*
     * \brief just the given row and col and based on given width and height,
     *        It draws a red box around the region.
     *
     */
    void drawBox(PNG& png, const int& row, const int& col, 
                     const int& width, const int& height);
};
#endif /* IMAGESEARCH_H */

