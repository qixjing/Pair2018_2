package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import test.strategy.GradeOneMath;
import test.strategy.GradeThreeMath;
import test.strategy.GradeTwoMath;

public class MathExam211506368Test {

	private static MathExam211506368 math = null;
	
	@BeforeClass
	public static void beforeClass(){
		if(math == null){
			math = new MathExam211506368();
		}
	}
	
	@Test
	public void testMathFactory1() {
		//一年级
		GradeOneMath one = (GradeOneMath) MathExam211506368.MathFactory(1);
		assertNotNull(one);
		assertTrue(one.getClass().equals(GradeOneMath.class));
		//二年级
	    GradeTwoMath two = (GradeTwoMath) MathExam211506368.MathFactory(2);
		assertNotNull(two);
		assertTrue(two.getClass().equals(GradeTwoMath.class));
		//三年级
		GradeThreeMath three = (GradeThreeMath) MathExam211506368.MathFactory(3);
		assertNotNull(three);
		assertTrue(three.getClass().equals(GradeThreeMath.class));
		
	
	}
	
	
	@Test
	@SuppressWarnings("static-access")
	public void testBuild() throws InterruptedException{
		math.build("10,3");
	}
	
	@Test
	public void testMain(){
		math.main(null);
	}
	
}
