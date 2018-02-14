package com.ChrisFrahm;

/**
 * @author Chris Frahm
 */
public class Main
    {
        /**
         * This program compares two .csv files.  places.csv is the driver file.
         * The field they share is country name.  Based on country name this program
         * will lists the number of cities each country has from the lists, and
         * number of stuff from each country
         */

        private static String stuffCSVSplitLine;
        private static String citiesCSVSplitLine;
        private final static FileInput countryNames = new FileInput("places.csv");
        private final static FileInput cityNames = new FileInput("places.csv");
        private final static FileInput stuffNames = new FileInput("stuff.csv");

        /**
         *  By looping through the places.csv file (driver) it can compare the element in fields[0] to
         *  the other files.  In addidiont a header section for the data to be displayed is created
         * @param args
         */
        public static void main(String[] args) {
           //holds string that will later be split on ","
            String placeRow;
            //an array of type string to hold the split fields from string of entire row
            String[] fields;
            //Iteration flag necessary to grab the first line and initiate process
            boolean first = true;
            //integer array to hold counters of total cities and total stuff
            int[] nums = new int[2];
            //headers
            System.out.format("%-22s %6s %5s\n","Country","Cities", "Stuff");
            System.out.format("%-22s %6s %5s\n","======","======", "======");
            //loop through countries file.  run methods findCityNames, findCountryStuff passing
            // fields[0] of driver file to be compared
            while ((placeRow = countryNames.fileReadLine()) != null) {
                fields = placeRow.split(",");
                findCityNames(first, fields[0], nums);
                findCountryStuff(first, fields[0], nums);
                //After going through once first can be set to false
                first = false;
                //to avoid having fields with 0 as value (creates duplicate results for each country)
                if(nums[0] != 0)
                {
                    System.out.format("%-21s    %-3d   %-3d\n", fields[0], nums[0], nums[1]);
                }
            }
            //close files when finished
            countryNames.fileClose();
            cityNames.fileClose();
            stuffNames.fileClose();
        }

        /**
         * By comparing the country name field[0] between the files, This program
         * will add one to the counter for that array item.
         * @param first set to true so it knows it is first line of file
         * @param country country corresponds to fields[0] of driver file, the country name
         * @param nums each element holds the number of cities per country
         */
        public static void findCityNames (boolean first, String country, int[] nums){

            //initialized to 0 for each new country
            nums[0] = 0;
            String[] fields;
            //set to false because we want it to go through loop at least once
            boolean done = false;
            //use first line if first is true
            if(first){
                citiesCSVSplitLine = cityNames.fileReadLine();
            }
            while ((citiesCSVSplitLine != null) && !(done))  {
                fields = citiesCSVSplitLine.split(",");
                if (fields[0].equals(country))  {
                    nums[0]++;
                    citiesCSVSplitLine = cityNames.fileReadLine();
                }   else if (!(fields[0].equals(country)))
                {
                    done = true;
                }
            }
        }

        /**
         *  findCountryStuff has a counter that totals the number of stuff per country based on
         *  if the stuff.csv
         * @param first set to true so it knows it is first line of file
         * @param country country corresponds to fields[0] of driver file, the country name
         * @param nums each element holds the number of stuff per country
         */
        public static void findCountryStuff (boolean first, String country, int[] nums){
            //initialized to 0 for each new country
            nums[1] = 0;
            String[] fields;
            //set to false because we want it to go through loop at least once
            boolean done = false;
            //use first line if first is true
            if(first){
                stuffCSVSplitLine = stuffNames.fileReadLine();
            }
            while ((stuffCSVSplitLine!= null) && !(done))  {
                fields = stuffCSVSplitLine.split(",");
                //couldn't get to work with .equals method, so I used .contains instead
                if (fields[0].contains(country))  {
                    nums[1]++;
                    stuffCSVSplitLine = stuffNames.fileReadLine();
                }   else if (!(fields[0].contains(country))){

                    done = true;

                }
            }


        }
    }
