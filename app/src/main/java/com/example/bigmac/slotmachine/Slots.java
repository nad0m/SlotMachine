package com.example.bigmac.slotmachine;
import java.lang.Math;

public class Slots
{
   public TripleString ts = new TripleString();

   /**
    * the TripleString object has to first be filled with three randomly chosen
    * Instantiates and returns a TripleString object to the client. The data of
    * strings ["cherries", "BAR" , "space"]
    *
    * @return TripleString
    */

   public TripleString pull()
   {
      ts.setString1(randString());
      ts.setString2(randString());
      ts.setString3(randString());

      return ts;
   }

   public String[] loadArray()
   {
      String[] strArr = {ts.getString1(), ts.getString2(), ts.getString3()};
      return strArr;
   }


   /**
    * Prompts user for bet and returns to it to main
    *
    * @return betAmount
    */
   public static boolean getBet(int betAmount)
   {
         if (betAmount < 0 || betAmount > 100)
         {
            return false;
         }
      return true;
   }

   /**
    * produces and returns a single random string: "BAR", "cherries", "space",
    * or "7".
    *
    * @return
    */
   private static String randString()
   {
      double rand = Math.random();
      if (rand < .125)
      {
         return "7";
      } else if (rand < .25)
      {
         return "(space)";
      } else if (rand < .50)
      {
         return "cherries";
      } else
      {
         return "BAR";
      }
   }

   /**
    * Returns the pay multiplier based on the results of a pull
    *
    * @param thePull
    * @return payMultiplier
    */
   public static int getPayMultiplier(TripleString thePull)
   {
      int payMultiplier = 0;
      if (thePull.getString2().equals(thePull.getString3())
         && thePull.getString2().equals(thePull.getString1()))
      {
         switch (thePull.getString1())
         {
         case "cherries":
            payMultiplier = 30;
            break;
         case "BAR":
            payMultiplier = 50;
            break;
         case "7":
            payMultiplier = 100;
            break;
         }
      } else if (thePull.getString1().equals("cherries"))
      {
         if (thePull.getString1().equals(thePull.getString2()))
         {
            payMultiplier = 15;
         } else
         {
            payMultiplier = 5;
         }
      }
      return payMultiplier;
   }

}

/**
 * This class keeps track of the three strings produced by a pull.
 *
 *
 */
class TripleString
{
   public static final int MAX_LEN = 20;

   private String string1;
   private String string2;
   private String string3;

   /**
    * a default constructor that initializes all string members to "". We do not
    * need any parameter-taking constructors.
    */
   public TripleString()
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }

   /**
    *
    * @param str
    * @return
    */
   private boolean validString(String str)
   {
      if (str != null)
      {
         return str.length() <= MAX_LEN;
      } else
      {
         return false;
      }
   }

   /**
    *
    * @return string1
    */
   public String getString1()
   {
      return string1;
   }

   /**
    *
    * @return string2
    */
   public String getString2()
   {
      return string2;
   }

   /**
    *
    * @return string3
    */
   public String getString3()
   {
      return string3;
   }

   /**
    * Sets the first string if is valid and returns true, otherwise returns
    * false
    *
    * @param string1
    * @return
    */
   public boolean setString1(String string1)
   {
      if (validString(string1))
      {
         this.string1 = string1;
         return true;
      } else
      {
         return false;
      }
   }

   /**
    *
    * @param string2
    * @return
    */
   public boolean setString2(String string2)
   {
      if (validString(string2))
      {
         this.string2 = string2;
         return true;
      } else
      {
         return false;
      }
   }

   /**
    *
    * @param string3
    * @return
    */
   public boolean setString3(String string3)
   {
      if (validString(string3))
      {
         this.string3 = string3;
         return true;
      } else
      {
         return false;
      }
   }

   /**
    *
    * @Override
    */
   public String toString()
   {
      return "[ " + string1 + " | " + string2 + " | " + string3 + " ]";
   }


}
