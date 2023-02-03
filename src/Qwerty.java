import java.util.Arrays;
import java.util.List;

public class Qwerty {

    final String[][] qwerty = {
            {"1","2","3","4","5","6","7","8","9","0"},
            {"q","w","e","r","t","y","u","i","o","p"},
            {"a","s","d","f","g","h","j","k","l",";"},
            {"z","x","c","v","b","n","m",",",".","/"}
    };
    public void encode(String encodingKeys, String text) {

        String[][] encoded = {
                {"1","2","3","4","5","6","7","8","9","0"},
                {"q","w","e","r","t","y","u","i","o","p"},
                {"a","s","d","f","g","h","j","k","l",";"},
                {"z","x","c","v","b","n","m",",",".","/"}
        };


        //Collect encoding keys after separating
        List<String> encodingKeysList = Arrays.asList(encodingKeys.split(","));
        for (String flipKey: encodingKeysList) {

            //if horizontal flip
            if (flipKey.equalsIgnoreCase("H")) {
                for (int i = 0; i < encoded.length; ++i) {
                    int lastIndex = encoded[i].length - 1;
                    for (int j = 0; j < encoded[i].length / 2; ++j) {
                        String temp = encoded[i][j];
                        encoded[i][j] = encoded[i][lastIndex];
                        encoded[i][lastIndex] = temp;

                        lastIndex = lastIndex - 1;
                    }
                }
            }
            //if vertical flip
            else if (flipKey.equalsIgnoreCase("V")) {
                for (int i = 0; i < encoded[0].length; ++i) {
                    int lastIndex = encoded.length - 1;
                    for (int j = 0; j < encoded.length / 2; ++j) {
                        String temp = encoded[j][i];
                        encoded[j][i] = encoded[lastIndex][i];
                        encoded[lastIndex][i] = temp;

                        lastIndex = lastIndex - 1;
                    }
                }
            } else {
                int k = 0;
                try {
                    k = Integer.parseInt(flipKey);
                } catch (Exception e) {
                    System.out.println("Invalid Input" + flipKey);
                }

                //if flipKey is positive then shift right
                if (k > 0) {
                    for (int i = 0; i < k; ++i) {

                        //vertical length i.e. => 4
                        int v = encoded.length;

                        //horizontal length i.e. => 10
                        int h = encoded[0].length;

                        //store last key i.e. => /
                        String last = encoded[v - 1][h - 1];

                        //initialize row with last horizontal index(3) and decrement
                        for (int row = v - 1; row >= 0; --row) {

                            //initialize column with last vertical index(9) and decrement
                            for (int col = h - 1; col >= 0; --col) {

                                //shift values

                                if ((row == 0) && (col == 0)) {
                                    //set last key to first
                                    encoded[0][0] = last;
                                } else {
                                    if (col == 0) {
                                        //for every first index of each row set prior row's last key
                                        encoded[row][col] = encoded[row - 1][h - 1];
                                    } else {
                                        //set prior column's key to current
                                        encoded[row][col] = encoded[row][col - 1];
                                    }
                                }
                            }
                        }
                    }
                }
                //if flipKey is negative then shift left
                else {
                    k = Math.abs(k);
                    for (int i = 0; i < k; ++i) {

                        //vertical length i.e. => 4
                        int v = encoded.length;

                        //horizontal length i.e. => 10
                        int h = encoded[0].length;

                        //store first key i.e. => /
                        String first = encoded[0][0];


                        //initialize row with last horizontal index(3) and decrement
                        for (int row = 0; row < v; ++row) {

                            //initialize column with last vertical index(9) and decrement
                            for (int col = 0; col < h; ++col) {

                                //shift values

                                if ((row == (v - 1)) && (col == (h - 1))) {
                                    //set first key to last
                                    encoded[v - 1][h - 1] = first;
                                } else {
                                    if (col == (h - 1)) {
                                        //for every last index of each row set next row's first key
                                        encoded[row][col] = encoded[row + 1][0];
                                    } else {
                                        //set next column's key to current
                                        encoded[row][col] = encoded[row][col + 1];
                                    }
                                }
                            }
                        }
                    }
                }


            }

            System.out.println("Encoded Qwerty with " + flipKey);
            print(encoded);
        }


        System.out.println("Text before => " + text);
        String updatedText = "";

        //loop over each character of text for encoding
        for (char ch: text.toCharArray()) {

            String chStr = String.valueOf(ch);
            if (chStr.equals(" ")) {
                updatedText = updatedText.concat(" ");
            }

            //find in qwerty keypad and update with encoded keypad at same index
            for (int i=0; i< qwerty.length; ++i) {
                for (int j=0; j< qwerty[i].length; ++j) {
                    if (qwerty[i][j].equalsIgnoreCase(chStr)) {
                        updatedText = updatedText.concat(encoded[i][j]);
                    }
                }
            }
        }

        System.out.println("Text after => " + updatedText);

    }

    public void print(String[][] qwerty) {
        for (String[] row: qwerty) {
            for (String s: row) {
                System.out.print(s);
                System.out.print("\t");
            }
            System.out.print("\n");

        }
    }

}
