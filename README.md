Simple implementation of TLV (Type-length-value) parser using Facory Desing pattern for flexibility of adding new operations.

The TLVParser class reads input from stdin. Tag is the interface that declares the function that we need each of its concrete class to implement. ReplaceTga and UpperCaseTag are the two comcrete classes that implement Tag interface. We use TagFactory class to instantate each of these classes based on the given inout tag type frm the TLVParser.

- I have used factory design pattern for the flexibility of creating the type of tag we want. 
- For each new type of tag, we can create a concrete class implementation of the Tag interface and 
  add a condition to the factory method to instantiate the new tag type. We return null if the requested 
  tag type is not defined. 
- Each of the two concrete classes here, ReplaceTag and UpperCaseTag, implement the tag interface. Each override 
  the processData method to do the performthe given operation of replacing the input string or to convert the 
  string to uppercase.
