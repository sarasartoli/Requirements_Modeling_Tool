# Requirements_Modeling_Tool
This tool is used to model and analyze socio-technical systems requirements. the tool  is developed using Java and SPARC programming languages. 

The program consists of a parser that takes as input a JSON file that represents a goal oriented requirements model. The parser generates an Answer Set Programming (ASP)_based goal model that also includes a domain-independent library of violations (i.e. as a set of Answer Set Programming rules).  The program then gets the output from the ASP solver, as shown in Figure 1. The output is  a list of ASP predicates that gives the violation found in the requirements model. the violation can be used to elicit more requirements or resolve conflicts and inconsistencies in the requirements model.

![Alt text](https://github.com/sarasartoli/Requirements_Modeling_Tool/blob/master/Program-Architecture.png)
 




