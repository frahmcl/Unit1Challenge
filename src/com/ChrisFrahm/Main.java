package com.ChrisFrahm;

    public class Main
    {
        private static String stuffCSVSplitLine;
        private static String citiesCSVSplitLine;
        private final static FileInput countryNames = new FileInput("places.csv");
        private final static FileInput cityNames = new FileInput("places.csv");
        private final static FileInput stuffNames = new FileInput("stuff.csv");

        public static void main(String[] args) {
            String placeRow;
            String[] fields;
            boolean first = true;
            int[] nums = new int[2];

            System.out.format("%-22s %6s %5s\n","Country","Cities", "Stuff");
            System.out.format("%-22s %6s %5s\n","*******","******", "******");
            while ((placeRow = countryNames.fileReadLine()) != null) {
                fields = placeRow.split(",");
                findCityNames(first, fields[0], nums);
                findCountryStuff(first, fields[0], nums);
                first = false;
                //System.out.format("%-21s    %-3d   %-3d\n", fields[0], nums[0], nums[1]);
                if(nums[0] != 0)
                {
                    System.out.format("%-21s    %-3d   %-3d\n", fields[0], nums[0], nums[1]);
                }
            }
        }


        public static void findCityNames (boolean first, String country, int[] nums){
            nums[0] = 0;
            String[] fields;
            boolean done = false;

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


        public static void findCountryStuff (boolean first, String country, int[] nums){

            nums[1] = 0;
            String[] fields;
            boolean done = false;
            if(first){
                stuffCSVSplitLine = stuffNames.fileReadLine();
            }
            while ((stuffCSVSplitLine!= null) && !(done))  {
                fields = stuffCSVSplitLine.split(",");
                if (!(fields[0].equals(country)))  {
                    done = true;
                }   else if (fields[0].equals(country)){
                    nums[1]++;
                    stuffCSVSplitLine = stuffNames.fileReadLine();
                }
            }


        }
    }
