# Data
This is where all the references to data are. We will also put our finished data in here maybe.

# Whats in here?
 - `2005_2009_curated.csv` curated census data for the 2005->2009 time range 
 - `2015_2019_curated.csv` curated census data for the 2015->2019 time range 
 - `20{0,1}5_20{0,1}9_config.ts` config.ts file used for the [Sustain Dataset Curator](https://github.com/Project-Sustain/sustain-dataset-curator) to turn the source files into the curated version 
 - `Get_Crash_Amounts.ipynb` jupyter notebook for curating the crash amounts from the zillow data
 - `crash_amounts.csv` curated crash data


# What is a GISJOIN?
GISJOIN IS VERY GOOD WAY TO GIVE A COUNTY AN ID SO WE USE IT

# Sources

## National Risk Index
This will mostly just be used for visualization at the end. It provides nice indexes that we can put next to ours.

[National Risk Index - All Counties US (17 MB)](https://drive.google.com/file/d/1kFoVlR8luzLfymJbS-V2suFnm9QGwTyd/view?usp=sharing)

[National Risk Index - Label Mappings](https://drive.google.com/file/d/1hLSPZ9CnAbcGlCg9aLNZ4AOq_Nqb7arw/view?usp=sharing)

## Zillow Home Value Index
"Typical home value" data for many counties across many months.

[Zillow Home Value Index - Counties (4.7 MB)](https://drive.google.com/file/d/1G8qPbVX1pWMjsXejS7T9Tve8qSQwuiz7/view?usp=sharing)

## American Community Survey Data 2005->2009 and 2015->2019
Each of these datasets include:
 - Total Population
 - Median Household Income
 - Median Ages
 - Gini Income Inequality Index
 - Race - Raw Numbers*
 - Educational Attainment By Sex** - Raw Numbers*
 - Ratio Of Income to Poverty Level - Raw Numbers*
 - Occupied Housing Units Rented/Owned (Tenure) - Raw Numbers*
 - Occupancy Status of Housing Units - Raw Numbers*
  
*All "Raw Numbers" will need to be normalized by their corresponding "Total"

**For some reason, the educational attainment is always split by sex for 2005->2009, so some pre-processing will need to be done for that one to make it agnostic to sex.

### 2005->2009
[Gini Index Data](https://drive.google.com/file/d/1qKTPLa5qQmXuW2-tHISZHUzHwkfPx9Td/view?usp=sharing)

[All Other Data](https://drive.google.com/file/d/1TxxDq_jed2nnzvRxqT_wm6QmRsfcgtpO/view?usp=sharing) -- [Metadata for all other data (Some labels are the same so this is necessary)](https://drive.google.com/file/d/16qrCSeSlWg_Mo0-s-ASN5W3kudh18BTK/view?usp=sharing)

### 2015->2019
[Gini Index Data](https://drive.google.com/file/d/1In_Gx28SxajGLFecu0joPWu16sAtrHe9/view?usp=sharing)

[All Other Data](https://drive.google.com/file/d/11W8vCYUbyTKDyCoiXSIP7DeSu5p9QNH7/view?usp=sharing) -- [Metadata for all other data (Some labels are the same so this is necessary)](https://drive.google.com/file/d/1HTYx3fKCjlwqiydiD8VDdQg43uWEs5wr/view?usp=sharing)
