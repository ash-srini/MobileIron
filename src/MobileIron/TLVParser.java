package MobileIron;
import java.io.*;
import java.util.*;

/**
 * Created by aishwaryasrinivasan on 06/10/16.
 */

/*
Write a TLV processor in Java

* Process TLVs with the following format.

* Format: TYPE(6)-LEN(4)-VALUE

* Type - fixed 6 chars length. Defines the type of processing required.

* Len - fixed 4 chars (represented as text) length of the value.

* Value - the actual data to be processed of length 'Len' above.

* Processing details:

* For Type UPPRCS: convert the value to uppercase, if already uppercase do nothing.

* For Type REPLCE: replace the value with fixed string "THIS STRING"

* For any other type indicate an error and continue processing.

* Assume all data is in ascii.

* Assume all data will always follow the format specified, i.e. no malformed TLVs.

* Input is specified on StdIn and output is expected on StdOut.

** Extra points for flexible design which will help to easily add new input-output types (like network streams), processors (for example, LOWRCS), or any other parts of the design.

* E.g.

Input:

UPPRCS-0005-abcde

REPLCE-0003-123

UPPRCS-0008-AbcdefghREPLCE-0003-123REPLCE-0001-Z

TAG001-0012-abcdefgh1234

UPPRCS-0004-1234

Output:

UPPRCS-ABCDE

REPLCE-THIS STRING

UPPRCS-ABCDEFGH

REPLCE-THIS STRING

REPLCE-THIS STRING

Type not valid

UPPRCS-1234
 */


public class TLVParser {
    public static String parseTLVInput(String type, int length, String value){
        //we use TagFactory to instantiate and return the required implementation of the Tag interface
        String output;
        Tag tag = TagFactory.getTag(type);
        /* The getTag method will return null if the type of tag we are requesting is not a valid type.
        * Check for null and print message if the type of tag given is invalid*/
        if (tag!=null)
            output = tag.processData(value);
        else
            return "Type not valid";
        return type+"-"+output;
    }

    public static void parseInput(String input){
        // check for null and empty inputs and remove '-' from the input
        input = input.replace("-","");
        int i = 0;

        /* Since it's given that the TLV will not be malformed we can be sure that the first 6 characters of the input string will represent the type,
         * the next 4 will represent the length. We iterate through the length of the input string to check if there are multiple tags on one line. */
        while(i < input.length()){
            for(int j=0; j<3; j++){
                String type = input.substring(i,i+6);
                i+=6; j++;
                int length = Integer.parseInt(input.substring(i,i+4));
                i+=4; j++;
                String value = input.substring(i, i+length);
                i+=length; j++;
                //each combination is passed to the tlv parser so that we can execute the give operation type
                System.out.println(parseTLVInput(type, length, value));
            }
        }
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        try {
            while (sc.hasNextLine() ) {
                String input = sc.nextLine();
                //check for empty lines
                if(input != null || !input.equals(" "))
                    //each line
                    parseInput(input);
            }
        }finally {
            sc.close();
        }
    }

}
