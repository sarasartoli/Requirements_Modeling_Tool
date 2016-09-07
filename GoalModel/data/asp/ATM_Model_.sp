sorts
#agent = {A1}./n#goal= {G1,G2,G3,G4,G5,G6,G7,G8}./n#task= {T1,T2,T3,T4,T5,T6,T7,T8,T9,T10}./n#context= {C1,C2,C3,C4,C5}.#decType ={andDec, orDec}.
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
ownsGoal(A1,G2).
ownsGoal(A1,G4).
ownsGoal(A1,G6).
ownsGoal(A1,G8).
goalType(G2,root).
goalType(G4,child).
goalType(G6,child).
goalType(G8,child).
goalDecomp(or,G1,G2,C1).
goalDecomp(or,G1,G3,C2).
goalDecomp(or,G1,G3,C3).
goalDecomp(and,G2,G4,null).
goalDecomp(and,G2,G5,C4).
goalDecomp(and,G3,G7,null).
goalDecomp(and,G3,G8,C5).
taskLink(G5,T1,null).
taskLink(G5,T2,null).
taskLink(G6,T3,null).
taskLink(G6,T4,null).
taskLink(G7,T5,null).
taskLink(G7,T6,null).
taskLink(G8,T7,null).
taskLink(G8,T8,null).
taskLink(G3,T9,null).
taskLink(G3,T10,null).
