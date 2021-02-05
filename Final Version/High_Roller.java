/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DrSinji
 */
import java.util.*;

class Task {
    int start, end, reward;

    Task(int start, int end, int reward) {
        this.start = start; //task start time
        this.end = end; //task end time
        this.reward = reward; //task reward weight
    }

    @Override
    public String toString() { //overrides obejct, used for last print statement
        return "(" + this.start + ", " + this.end + ") ";
    }
}

class High_Roller {
    public static void Hustle(List<Task> schedule) { //method to find the longest or maximum reward, that fits into the schedule
        
        Collections.sort(schedule, Comparator.comparingInt(x -> x.start)); //sorts task start time into increasing order
        
        int scheduleSize = schedule.size(); //sets static schedule size for indexing

        List<List<Integer>> tasks = new ArrayList<>(); 
        for (int i = 0; i < scheduleSize; i++) { //finds non conflicting tasks ending with i'th task
            tasks.add(new ArrayList<>());
        }
        
        int[] maxReward = new int[scheduleSize]; //stores total reward of tasks in tasks[i]

        for (int i = 0; i < scheduleSize; i++) { //checks each task           
            for (int j = 0; j < i; j++) { //check each j less then i

                if (schedule.get(j).end <= schedule.get(i).start && maxReward[i] < maxReward[j]) {
                    tasks.set(i, new ArrayList<>(tasks.get(j))); //if j'th job doesn't conflict, update i'th job, all in bounds of max reward
                    maxReward[i] = maxReward[j];
                }
            }
            
            tasks.get(i).add(i); //finishes current task with i'th task
            maxReward[i] += schedule.get(i).reward;
        }

        int index = 0;
        for (int i = 1; i < scheduleSize; i++) {
            if (maxReward[i] > maxReward[index]) { //used to find max reward index
                index = i; 
            }
        }

	int freedom = 0; //optimal end reward of compatible schedule
        System.out.print("Your optimal schedule is: \n");
        for (Integer i: tasks.get(index)) {
            System.out.print(schedule.get(i)); //uses toString to print optimal schedule
            freedom += schedule.get(i).reward; //adds reward to freedom for optimal reward
        }
        System.out.println("\nHustlin' like this gives you an maximized reward of " + freedom);
    }

    public static void main(String[] args) {
        List<Task> schedule = Arrays.asList(
                        new Task(5, 6, 60),
                        new Task(9, 17, 25),
                        new Task(6, 13, 85),
                        new Task(8, 11, 45),
                        new Task(10, 13, 55),
                        new Task(14, 17, 35),
                        new Task(13, 16, 40),
                        new Task(16, 20, 30),
                        new Task(13, 18, 75),
                        new Task(17, 20, 35),
                        new Task(18, 24, 85)
        );
        Hustle(schedule);
    }
}
