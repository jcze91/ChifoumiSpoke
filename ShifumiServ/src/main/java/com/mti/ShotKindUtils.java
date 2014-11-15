package com.mti;


 public class ShotKindUtils
 {
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