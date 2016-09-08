package com.company.aspwriter;

import com.company.model.GoalModel;
import com.company.model.attributes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by E5-571 on 7/25/2016.
 */
public class ASPGoalFileWriter {

    public void writeToASP(GoalModel goalModel, String fileTitle, String aspDestination) {
        ArrayList<DomainAgent> agents = goalModel.getDomainAgentList();
        ArrayList<Goal> goals = goalModel.getGoalList();
        ArrayList<DomainContext> context = goalModel.getContextList();
        ArrayList<ActionTask> tasks = goalModel.getActionTaskList();
        ArrayList<GoalOwner> ownsGoal = goalModel.getGoalOwnerList();
        ArrayList<GoalDecomposition> goalDecomp = goalModel.getGoalDecompositionList();
        ArrayList<TaskLink> taskLinks = goalModel.getTaskLinkLisk();
        ArrayList<TaskDecomposition> taskDec = goalModel.getTaskDecompositionList();

        String aspFile = aspDestination + "\\" + fileTitle.replace(" ", "_") + ".sp";
        File outputFile = new File(aspFile) ;

        if (outputFile.exists() && outputFile.isFile()) {
                System.out.println("Rename file " + outputFile + " already exist in selected directory");
            } else {
            try {

                PrintWriter writer = new PrintWriter(outputFile);
                writer.println("sorts");

                writer.print("#agent = {");
                for (int i = 0; i < agents.size(); i++) {
                    DomainAgent a = agents.get(i);
                    writer.print(a.getId());
                    if (i != (agents.size() - 1)) {
                        writer.print(",");
                    } else {
                        writer.println("}.");
                    }
                }

                writer.print("#goal= {");
                for (int i = 0; i < goals.size(); i++) {
                    Goal g = goals.get(i);
                    writer.print(g.getId());
                    if (i != (goals.size() - 1)) {
                        writer.print(",");
                    } else {
                        writer.println(",noGoal}.");
                    }
                }

                writer.print("#task= {");
                for (int i = 0; i < tasks.size(); i++) {
                    ActionTask t = tasks.get(i);
                    writer.print(t.getId());
                    if (i != (tasks.size() - 1)) {
                        writer.print(",");
                    } else {
                        writer.println(",noTask}.");
                    }
                }

                writer.print("#context= {");
                for (int i = 0; i < context.size(); i++) {
                    DomainContext c = context.get(i);
                    writer.print(c.getId());
                    if (i != (context.size() - 1)) {
                        writer.print(",");
                    } else {
                        writer.println(",noContext}.");
                    }
                }
                writer.println("#decType ={andDec, orDec, noDec}.");
                writer.println("#goalType = {root, child}.");

                writer.println("\npredicates");

                writer.println("ownsGoal(#agent, #goal).");
                writer.println("goalDec(#decType, #goal, #goal, #context).");
                writer.println("taskLink(#goal, #task, #context).");
                writer.println("taskDec(#decType, #task, #task, #context).");
                writer.println("contextDec(#decType, #context, #context).");
                writer.println("goalType(#goal, #goalType).");
                writer.println("goalSingleDecomposition(#goal, #goal).");
                writer.println("hasMoreGoalDecomposition(#goal, #goal).");
                writer.println("taskSingleDecomposition(#task, #task).");
                writer.println("hasMoreTaskDecomposition(#task, #task).");
                writer.println("inGoalCycle(#goal).");
                writer.println("reachableGoal(#goal, #goal).");
                writer.println("inTaskCycle(#task).");
                writer.println("reachableTask(#task, #task).");
                writer.println("hasOwner(#goal).");
                writer.println("noOwnership(#goal).");
                writer.println("noRefinedGoal(#goal).");
                writer.println("isRefinedGoal(#goal).");
                writer.println("hasTaskDecomposition(#goal).");
                writer.println("hasGoalDecomposition(#goal).");
                writer.println("taskNotJustified(#task).");
                writer.println("taskJustified(#task).");


                writer.println("\nrules");

                //violations rules
                writer.println("goalSingleDecomposition(X,Y) :- goalDec(D,X,Y,C), not hasMoreGoalDecomposition(X,Y).");
                writer.println("hasMoreGoalDecomposition(X,Y) :- goalDec(D,X,Z,C), Y!=Z.");
                writer.println("taskSingleDecomposition(T,U) :- taskDec(D,T,U, C), not hasMoreTaskDecomposition(T,U).");
                writer.println("hasMoreTaskDecomposition(T,U):- taskDec(D,T,V,C), U!=V.");
                writer.println("inGoalCycle(G) :- reachableGoal(G,G).");
                writer.println("reachableGoal(G1, G2) :- goalDec(D, G1,G2, C).");
                writer.println("reachableGoal(G1, G2) :- goalDec(D, G1, G3, C), reachableGoal(G3, G2).");
                writer.println("inTaskCycle(T) :- reachableTask(T1, T1).");
                writer.println("reachableTask(T1, T2) :- taskDec(D, T1, T2, C).");
                writer.println("reachableTask(T1, T2) :- taskDec(D, T1, T3, C), reachableTask(T3, T2).");
                writer.println("noOwnership(G) :- not hasOwner(G).");
                writer.println("hasOwner(G) :- ownsGoal(A, G).");
                writer.println("noRefinedGoal(G) :- goalType(G, T), not isRefinedGoal(G).");
                writer.println("isRefinedGoal(G) :- hasTaskDecomposition(G).");
                writer.println("isRefinedGoal(G) :- hasGoalDecomposition(G).");
                writer.println("hasTaskDecomposition(G) :- taskLink(G, T, C).");
                writer.println("hasGoalDecomposition(G) :- goalDec(D, G, G1, C).");
                writer.println("taskNotJustified(T) :- not taskJustified(T).");
                writer.println("taskJustified(T) :- taskLink(G,T,C). ");

                writer.println("");
                //owns goals rules
                ownsGoal.forEach(ownsGoalIt -> {
                    writer.println("ownsGoal(" + ownsGoalIt.getAgent() + "," + ownsGoalIt.getGoal() + ").");
                });

                writer.print("\n");
                //root Goals
                goals.forEach(goalsIterator ->{
                    String type;
                    if (goalsIterator.isRootGoal()) {
                        type = "root";
                    } else {
                        type = "child";
                    }
                    writer.println("goalType(" + goalsIterator.getId() + "," + type + ").");
                });

                writer.print("\n");
                //write goal decomposition links
                goalDecomp.forEach(goalDecomposition -> {
                    //Lood through the targetList
                    //targetList[0]  = targetID
                    //targetList[0]  = contextID
                    goalDecomposition.getTargetList().forEach(targetList -> {
                        writer.println("goalDec(" + goalDecomposition.getDecType() + "," + goalDecomposition.getSourceGoalID()
                                + "," + checkTargetList(targetList) + ").");
                    });
                });

                writer.print("\n");
                //write task links
                taskLinks.forEach(taskLink -> {
                    //Lood through the targetList
                    //targetList[0]  = targetID
                    //targetList[0]  = contextID
                    taskLink.getTargetList().forEach(targetList -> {
                        writer.println("taskLink(" + taskLink.getSourceGoalID()
                                + "," + checkTargetList(targetList) + ").");
                    });
                });

                writer.print("\n");
                //write task decomposition
                taskDec.forEach(taskD -> {
                    //Lood through the targetList
                    //targetList[0]  = targetID
                    //targetList[0]  = contextID
                    taskD.getTargetList().forEach(targetList -> {
                        writer.println("taskDec(" + taskD.getDecType() + "," + taskD.getSourceTaskID()
                                + "," + checkTargetList(targetList) + ").");
                    });
                });

                writer.print("\n");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("Goal model JSON to ASP conversion completed");
        }

    }

    private String checkTargetList(String[] targetDesc) {

        String targetListVal = "";
        switch (targetDesc.length){
            case 1:
                targetListVal = targetDesc[0];
                break;
            case 2:
                targetListVal = targetDesc[0] + "," + targetDesc[1];
                break;

        }
        return targetListVal;
    }

}

