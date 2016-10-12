Simple implementation of TLV (Type-length-value) parser using Facory Desing pattern for flexibility of adding new operations.

- I have used factory design pattern for the flexibility of creating the type of tag we want. 
- For each new type of tag, we can create a concrete class implementation of the Tag interface and 
  add a condition to the factory method to instantiate the new tag type. We return null if the requested 
  tag type is not defined. 
- Each of the two concrete classes here, ReplaceTag and UpperCaseTag, implement the tag interface. Each override 
  the processData method to do the performthe given operation of replacing the input string or to convert the 
  string to uppercase.
