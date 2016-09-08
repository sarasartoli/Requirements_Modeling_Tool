sorts
#agent = {a1}.
#goal= {g1,g2,g3,g4,g5,g6,g7,g8}.
#task= {t1,t2,t3,t4,t5,t6,t7,t8,t9,t10}.
#context= {c1,c2,c3,c4,c5}.
#decType ={andDec, orDec}.
#goalType = {root, child}.

predicates
ownsGoal(#agent, #goal).
goalDec(#decType, #goal, #goal, #context).
taskLink(#goal, #task, #context).
taskDec(#decType, #task, #task, #context).
contextDec(#decType, #context, #context).
goalType(#goal, #goalType).
goalSingleDecomposition(#goal, #goal).
hasMoreGoalDecomposition(#goal, #goal).
taskSingleDecomposition(#task, #task).
hasMoreTaskDecomposition(#task, #task).
inGoalCycle(#goal).
reachableGoal(#goal, #goal).
inTaskCycle(#task).
reachableTask(#task, #task).
hasOwner(#goal).
noOwnership(#goal).
noRefinedGoal(#goal).
isRefinedGoal(#goal).
hasTaskDecomposition(#goal).
hasGoalDecomposition(#goal).
taskNotJustified(#task).
taskJustified(#task).

rules
goalSingleDecomposition(X,Y) :- goalDec(D,X,Y,C), not hasMoreGoalDecomposition(X,Y).
hasMoreGoalDecomposition(X,Y) :- goalDec(D,X,Z,C), Y!=Z.
taskSingleDecomposition(T,U) :- taskDec(D,T,U, C), not hasMoreTaskDecomposition(T,U).
hasMoreTaskDecomposition(T,U):- taskDec(D,T,V,C), U!=V.
inGoalCycle(G) :- reachableGoal(G,G).
reachableGoal(G1, G2) :- goalDec(D, G1,G2, C).
reachableGoal(G1, G2) :- goalDec(D, G1, G3, C), reachableGoal(G3, G2).
inTaskCycle(T) :- reachableTask(T1, T1).
reachableTask(T1, T2) :- taskDec(D, T1, T2, C).
reachableTask(T1, T2) :- taskDec(D, T1, T3, C), reachableTask(T3, T2).
noOwnership(G) :- not hasOwner(G).
hasOwner(G) :- ownsGoal(A, G).
noRefinedGoal(G) :- goalType(G, T), not isRefinedGoal(G).
isRefinedGoal(G) :- hasTaskDecomposition(G).
isRefinedGoal(G) :- hasGoalDecomposition(G).
hasTaskDecomposition(G) :- taskLink(G, T, C).
hasGoalDecomposition(G) :- goalDec(D, G, G1, C).
taskNotJustified(T) :- not taskJustified(T).
taskJustified(T) :- taskLink(G,T,C). 

ownsGoal(a1,g1).
ownsGoal(a1,g2).
ownsGoal(a1,g3).
ownsGoal(a1,g4).
ownsGoal(a1,g5).
ownsGoal(a1,g6).
ownsGoal(a1,g7).
ownsGoal(a1,g8).

goalType(g1,root).
goalType(g2,child).
goalType(g3,child).
goalType(g4,child).
goalType(g5,child).
goalType(g6,child).
goalType(g7,child).
goalType(g8,child).

goalDec(orDec,g1,g2,c1).
goalDec(orDec,g1,g3,c2).
goalDec(orDec,g1,g3,c3).
goalDec(andDec,g2,g4,null).
goalDec(andDec,g2,g5,c4).
goalDec(andDec,g3,g7,null).
goalDec(andDec,g3,g8,c5).

taskLink(g5,t1,null).
taskLink(g5,t2,null).
taskLink(g6,t3,null).
taskLink(g6,t4,null).
taskLink(g7,t5,null).
taskLink(g7,t6,null).
taskLink(g8,t7,null).
taskLink(g8,t8,null).
taskLink(g3,t9,null).
taskLink(g3,t10,null).


