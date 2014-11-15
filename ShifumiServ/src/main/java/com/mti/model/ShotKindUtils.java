package com.mti.model;

public class ShotKindUtils
 {
     /**
      * Function permettant d'avoir le type de coup à partir d'une string
      * @param s
      * @return le type de coup désigné par la string
      */
     public static ShotKind ConvertFromString(String s)
     {
         switch (s){
             case "PAPER":
              return ShotKind.PAPER;
             case "ROCK":
              return ShotKind.ROCK;
             case "SCISSORS":
              return ShotKind.SCISSORS;
             case "SPOKE":
                 return ShotKind.SPOKE;
             case "LIZARD":
                 return ShotKind.LIZARD;
             default:
                 return null;
          }
    }
 }