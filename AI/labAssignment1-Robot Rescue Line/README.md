Assignment Description:  
**Part1:**  
Build a program that enables your robot to follow a black line on a light background. The line will be 1-2 cm wide. Develop your approach to cope with different lines (straight, curved or a mixture) and try to determine where it breaks. You will be given some sample lines to try your robot on. Make sure your robot works well on a range of lines, and perform quantitative testing on the more challenging ones (e.g. telling us it worked 7 times out of 10).  
**Part2:**  
Develop your robot system from Part 1 to make a decision on which way to turn at a junction, based upon a set of coloured-tape signals. An intersection in the path may occur anywhere over the course. Intersection markers will be denoted by green tape, approximately 3cmx3cm in dimension. These are used to indicate the direction of the path that the robot should follow. A piece of red tape on either side of the line indicates that the course has ended.  
  
1. If there is no green marker, the robot should carry on straight ahead.  
2.  A dead end may occur where there are two green markers before an intersection, on each side of the line. If this is encountered, your robot should turn around.  
3. Intersections are always perpendicular and may have 3 or 4 branches.  
4. Intersection markers will be placed just before the intersection.  
5. If a red intersection marker is detected on both sides of the line your robot has reached the end of the course and must stop. Only one piece, on one side of the line, does not indicate a stop, and the robot should keep on going.  
  
**Part3:**  
Develop your robot system from Parts 1 and 2 to navigate around an obstacle on a path. Obstacles may consist of bricks, blocks, weights and other large objects. Obstacles will be at least 10cm high. Given the detection of an object, your robot must navigate around this, and join back up to a line, and carry on. Your robot will get no marks if it does anything else, such as pushing an obstacle out of the way. Therefore, please use the appropriate sensor(s) to keep a certain distance from an object.  
**Part4:**  
Develop your robot system from Parts 1, 2 and 3 to cope with path-following when there is a gap in the path. Over the course of the path, your line may end abruptly. This line will start up again a short distance from where it ended, straight ahead from where it ended. Your task is to develop an approach to this problem that is able to cope with variations in gap size. The length of a gap will be no longer than 20cm, and the path will be at least 5cm in length before there is a gap.  
  
The "aiass1.xml" is the final submission to this assignment.