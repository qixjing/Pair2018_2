public class MathExam {
       public static void main(String[] args) {
    	   Parameter parameter = new Parameter(args);
    	   TaskMake make = new TaskMake();
    	   switch(parameter.GetGrade()) {
    	   case 1:{
    		   make.MakeGradeOne(parameter.GetNumber());
    		   break;
    	   }
    	   case 2:{
    		   make.MakeGradeTwo(parameter.GetNumber());
    		   break;
    	   }
    	   case 3:{
    		   make.MakeGradeThree(parameter.GetNumber());
    		   break;
    	   }
    	   /*
    	   default:{
    		   System.out.println("Error:Unexpected Error");
    	   }
    	   */
    		   
    	   }
       }

  }