public class MyUtils
{
  // check if string contain number
  public static Integer stringContainDigit(final String str) {                
    
    if(StringUtils.hasText(str))
    {
      StringBuilder sb = new StringBuilder();
      for(char c : str.toCharArray()){
          if(Character.isDigit(c)){
              return 1;
          } else {
              return 0;                
          }
      }
    }
    
    return -1;
}
}
