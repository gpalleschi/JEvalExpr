package com.gpsoft.jevalexpr.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.gpsoft.jevalexpr.DataValue;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.Step;
import com.gpsoft.jevalexpr.Token;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.Utility;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.Variable;
import com.gpsoft.jevalexpr.functions.*;
import com.gpsoft.jevalexpr.log.Logger;

/**
 * This class is the heart of this library and permits to parse, check and execute an input expression
 * 
 * @author Giovanni Palleschi
 * @version 1.0.0
 *
 */

public class Expression  { 
	private String humanExpr;
	private ArrayList<Variable<?>> variables;
	
	ArrayList<Token<?>> tokens = new ArrayList<Token<?>>();
	ArrayList<Token<?>> natTokens;
	
	ExpBin<Object> expBin = new ExpBin<Object>();
	
	public ExpBin<Object> getExpBin() {
		return expBin;
	}

	public Expression(String expr, ArrayList<Variable<?>> var) {
		super();
		this.humanExpr = expr;
		this.variables = var;
	}
	
	/**
	 * This function check if input variables are duplicated or the name of variable is a key word.<br>
	 * 
	 * @return result of check (true if is all ok or false if is nok)
	 * 
	 */
	
	private boolean initCompiling() {
		boolean ret = true;
		int iTotVariableName = 0;
	
		// Check if variables not presents in key words
		for(Variable<?> variable : variables ) {
			if ( Functions.names.containsKey(variable.getName()) ) {
				ret = false;
				Logger.error("Variable '" + variable.getName() + "' is a key word." );
				break;
			}	
			
			// Check Duplication
            iTotVariableName = 0;
			for(Variable<?> variableToComp : variables ) {
				if ( variable.getName().equals(variableToComp.getName()) ) {
					iTotVariableName++;
					if ( iTotVariableName > 1 ) {
						ret = false;
						Logger.error("Variable '" + variable.getName() + "' is duplicated." );
						break;						
					}
				}
			}
			
		}	
		
		return ret;
	}	
	
	/**
	 * This method analyzes expression to divide it in tokens 
	 * 
	 * @param expPos : current position
	 * @return current position of character analyzed in expression
	 */
	
	private int getToken(int expPos) {
		
		Set<Character> specialChars = new HashSet<>(Arrays.asList('?',':','-','+','*','/','%','&','=','<','>','!','|','^'));
		
		Logger.debug("Carattere : (" + humanExpr.charAt(expPos) + ')');
		
		// Jump all spaces
		while( humanExpr.charAt(expPos) == ' ' ) {
			expPos++;
			// End Expression
			if ( expPos == humanExpr.length() ) return 0;
		}
		
		// Constant String 
		if ( humanExpr.charAt(expPos) == '\'' ) {
			
			int retIdx = Utility.getString(humanExpr, expPos, '\'');
			
			if ( retIdx < 0 ) {
				 Logger.error("Error in getToken.");
				 return -1;
			}
			
			tokens.add(new Token<Object>(humanExpr.substring(expPos+1,retIdx), // String tokenName, 
					             TypeToken.E_value, // TypeToken typeToken, 
					             OperatorSyntaxType.E_undef, // OperatorSyntaxType opeartorSyntaxType,
					             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
					             0, // int idxPartOpe, 
					             ValueType.E_nat, // ValueType valueType,
					             TypeStep.E_constant, // TypeStep typeStep, 
					             0, // int stepRef,
					             TypeData.E_string,
					             humanExpr.substring(expPos+1,retIdx), // T value, 
					             0,
					             new FConstant())); // int nativeIdx
			return retIdx+1;
			
		}
		
		// Number Constant 
		if ( humanExpr.charAt(expPos) == '.' || Character.isDigit(humanExpr.charAt(expPos)) ) {
			Logger.debug("(" + humanExpr.charAt(expPos) +") Is Digit or .");
			boolean isDouble = false;
			int startPos = expPos;
			
			while(true) {
				
				if ( humanExpr.charAt(startPos) == '.' ) {
					if ( isDouble ) {
						Logger.error("Error in getToken.");
						return -1;
					}
					Logger.debug("Is Double");
					isDouble = true;
				} else {
					if ( startPos + 1 < humanExpr.length() && !Character.isDigit(humanExpr.charAt(startPos)) ) {
						break;
					}
				}
				
				if ( startPos + 1 == humanExpr.length() ) break;
				startPos++;
			}
			
    		if ( expPos == startPos )  startPos++;
			
// TODO: When in "1.2" don't work			
			
//			Logger.debug("expPos : " + expPos + " startPos : " + startPos);
			
			//TODO: check if only one character and value is "."
			
			Logger.debug("Numeric Value : " + humanExpr.substring(expPos,startPos));
			
		    if ( isDouble ) {
			    if ( startPos + 1 == humanExpr.length() && (Character.isDigit(humanExpr.charAt(startPos)) || humanExpr.charAt(startPos) == '.') ) startPos++;
			    
			    Logger.debug("Numeric Value : " + humanExpr.substring(expPos,startPos));
				tokens.add(new Token<Object>(humanExpr.substring(expPos,startPos), // String tokenName, 
			             TypeToken.E_value, // TypeToken typeToken, 
			             OperatorSyntaxType.E_undef, // OperatorSyntaxType opeartorSyntaxType,
			             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
			             0, // int idxPartOpe, 
			             ValueType.E_nat, // ValueType valueType,
			             TypeStep.E_constant, // TypeStep typeStep, 
			             0, // int stepRef,
			             TypeData.E_double,
			             Double.valueOf(humanExpr.substring(expPos,startPos)), // T value, 
			             0,
			             new FConstant())); // int nativeIdx
		    } else {
		    	if ( startPos + 1 == humanExpr.length() && Character.isDigit(humanExpr.charAt(startPos)) ) startPos++;
				tokens.add(new Token<Object>(humanExpr.substring(expPos,startPos), // String tokenName, 
			             TypeToken.E_value, // TypeToken typeToken, 
			             OperatorSyntaxType.E_undef, // OperatorSyntaxType opeartorSyntaxType,
			             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
			             0, // int idxPartOpe, 
			             ValueType.E_nat, // ValueType valueType,
			             TypeStep.E_constant, // TypeStep typeStep, 
			             0, // int stepRef,
			             TypeData.E_int,
			             Integer.valueOf(humanExpr.substring(expPos,startPos)), // T value, 
			             0,
			             new FConstant())); // int nativeIdx
		    }
		
			return startPos;
			
		}
	
		// Open Parenthesis
		if ( humanExpr.charAt(expPos) == '(' ) {
			tokens.add(new Token<Object>("(", // String tokenName, 
		             TypeToken.E_lbrc, // TypeToken typeToken, 
		             OperatorSyntaxType.E_undef, // OperatorSyntaxType opeartorSyntaxType,
		             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
		             0, // int idxPartOpe, 
		             ValueType.E_nat, // ValueType valueType,
		             TypeStep.E_constant, // TypeStep typeStep, 
		             0, // int stepRef,
		             TypeData.E_string,
		             null,
		             0)); // int nativeIdx
			return ++expPos;
		}
	    
		// Comma
		if ( humanExpr.charAt(expPos) == ',' ) {
			tokens.add(new Token<Object>(",", // String tokenName, 
		             TypeToken.E_comma, // TypeToken typeToken, 
		             OperatorSyntaxType.E_undef, // OperatorSyntaxType opeartorSyntaxType,
		             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
		             0, // int idxPartOpe, 
		             ValueType.E_nat, // ValueType valueType,
		             TypeStep.E_constant, // TypeStep typeStep, 
		             0, // int stepRef,
		             TypeData.E_string,
		             null,
		             0)); // int nativeIdx
			return ++expPos;
		}

		// Close Parenthesis
		if ( humanExpr.charAt(expPos) == ')' ) {
			tokens.add(new Token<Object>(")", // String tokenName, 
		             TypeToken.E_rbrc, // TypeToken typeToken, 
		             OperatorSyntaxType.E_undef, // OperatorSyntaxType opeartorSyntaxType,
		             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
		             0, // int idxPartOpe, 
		             ValueType.E_nat, // ValueType valueType,
		             TypeStep.E_constant, // TypeStep typeStep, 
		             0, // int stepRef,
		             TypeData.E_string,
		             null,
		             0)); // int nativeIdx
			return ++expPos;
		}
		
		// Special Characters
		if ( specialChars.contains(humanExpr.charAt(expPos))  ) {
			
			int op_syn_type = OperatorSyntaxType.E_undef;
			int op_prio = OperatorPriority.E_lev0;
			TypeStep step_type = TypeStep.E_ge;
			int idx_part_op = 0;
			Function funToAdd = null;
			
			switch(humanExpr.charAt(expPos)) {
		      case '-':
		      case '+':
		      {
		    	// Two Elements 
		    	if ( tokens.get(tokens.size()-1).getTypeToken() == TypeToken.E_value ||
		    		 tokens.get(tokens.size()-1).getTypeToken() == TypeToken.E_rbrc ) {
		    		op_syn_type = OperatorSyntaxType.E_two;
		    		op_prio = OperatorPriority.E_lev1;
		    		if (humanExpr.charAt(expPos) == '+' ) {
		    			step_type = TypeStep.E_sum;
		    			funToAdd = new FSum(); 
		    		} else {
		    			step_type = TypeStep.E_sub;
		    			funToAdd = new FSub(); 
		    		}
		    		
		    	} else {
		    	// Unary 
		    		op_syn_type = OperatorSyntaxType.E_one;
		    		op_prio = OperatorPriority.E_lev0;
		    		if (humanExpr.charAt(expPos) == '+' ) {
		    			step_type = TypeStep.E_unary_plus;
		    			funToAdd = new FUnaryPlus(); 
		    		} else {
		    			step_type = TypeStep.E_unary_minus;
		    			funToAdd = new FUnaryMinus(); 
		    		}
		    		
		    	}

				tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)), // String tokenName, 
				             TypeToken.E_op, // TypeToken typeToken, 
				             op_syn_type, // OperatorSyntaxType opeartorSyntaxType,
				             op_prio, // OperatorPriority operatorPriority, 
				             0, // int idxPartOpe, 
				             ValueType.E_nat, // ValueType valueType,
				             step_type, // TypeStep typeStep, 
				             0, // int stepRef,
           		             TypeData.E_string,
				             null,
				             tokens.size(),
						     funToAdd)); // int nativeIdx
				break;

		      }
		      case '^':
		      {
		    	funToAdd = new FExp(); 		    	  
				tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)), // String tokenName, 
				             TypeToken.E_op, // TypeToken typeToken, 
				             OperatorSyntaxType.E_two, // OperatorSyntaxType opeartorSyntaxType,
				             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
				             0, // int idxPartOpe, 
				             ValueType.E_nat, // ValueType valueType,
				             TypeStep.E_exp, // TypeStep typeStep, 
				             0, // int stepRef,
           		             TypeData.E_string,
				             null,
				             tokens.size(),
				             funToAdd)); // int nativeIdx		    	  
				break;
		      }
		      case '?':
		      case ':':
		      {
		        funToAdd = new FIfThenElse(); 		  
		    	if ( '?' == humanExpr.charAt(expPos) ) {
		    		idx_part_op = 0;
		    	}
		    	else {
		    		idx_part_op = 1;
		    	}

				tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)), // String tokenName, 
			             TypeToken.E_op, // TypeToken typeToken, 
			             OperatorSyntaxType.E_three, // OperatorSyntaxType opeartorSyntaxType,
			             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
			             idx_part_op, // int idxPartOpe, 
			             ValueType.E_nat, // ValueType valueType,
			             TypeStep.E_if_then, // TypeStep typeStep, 
			             0, // int stepRef,
           		         TypeData.E_string,
			             null,
			             tokens.size(),
			             funToAdd)); // int nativeIdx
				break;
		      }
		      case '*':
		      case '/':
		      case '%':
		      case '&':
		      case '=':
		      {
		    	if ( '*' == humanExpr.charAt(expPos) ) {
		    	  step_type = TypeStep.E_mul;
		    	  op_prio = OperatorPriority.E_lev0;
		    	  funToAdd = new FMul(); 
		    	 }

		    	if ( '/' == humanExpr.charAt(expPos) ) {
		    	  step_type = TypeStep.E_div;
		    	  op_prio = OperatorPriority.E_lev0;
		    	  funToAdd = new FDiv(); 
		    	}

		    	if ( '%' == humanExpr.charAt(expPos) ) {
		    	  step_type = TypeStep.E_mod;
		    	  op_prio = OperatorPriority.E_lev0;
		    	  funToAdd = new FMod(); 
		    	 }

		    	if ( '&' == humanExpr.charAt(expPos) )
		    	{
		    	  step_type = TypeStep.E_and;
		    	  op_prio = OperatorPriority.E_lev3;
		    	  funToAdd = new FAnd(); 
		    	}

		    	if ( '=' == humanExpr.charAt(expPos) )
		    	{
		    	  step_type = TypeStep.E_eq;
		    	  op_prio = OperatorPriority.E_lev2;
		    	  funToAdd = new FEq(); 
		    	}

				tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)), // String tokenName, 
				             TypeToken.E_op, // TypeToken typeToken, 
				             OperatorSyntaxType.E_two, // OperatorSyntaxType opeartorSyntaxType,
				             op_prio, // OperatorPriority operatorPriority, 
				             0, // int idxPartOpe, 
				             ValueType.E_nat, // ValueType valueType,
				             step_type, // TypeStep typeStep, 
				             0, // int stepRef,
             		         TypeData.E_string,
				             null,
				             tokens.size(),
				             funToAdd)); // int nativeIdx
				break;
		    	  
		      }
		      case '<':
		      case '>':
		      case '!':
		      {
		    	  if ( expPos + 1 < humanExpr.length() && '=' == humanExpr.charAt(expPos + 1) ) {
		    		  op_prio = OperatorPriority.E_lev2;
		    		  
		    		  if ( '<' == humanExpr.charAt(expPos) ) {
		    			  step_type = TypeStep.E_le;
		    			  funToAdd = new FLe();
		    		  }

		    		  if ( '>' == humanExpr.charAt(expPos) ) {
		    			  step_type = TypeStep.E_ge;
		    			  funToAdd = new FGe();
		    		  }

		    		  if ( '!' == humanExpr.charAt(expPos) ) {
		    			  step_type = TypeStep.E_ne;
		    			  funToAdd = new FNe();
		    		  }

					  tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)) + "=", // String tokenName, 
					             TypeToken.E_op, // TypeToken typeToken, 
					             OperatorSyntaxType.E_two, // OperatorSyntaxType opeartorSyntaxType,
					             op_prio, // OperatorPriority operatorPriority, 
					             0, // int idxPartOpe, 
					             ValueType.E_nat, // ValueType valueType,
					             step_type, // TypeStep typeStep, 
					             0, // int stepRef,
                 		         TypeData.E_string,
					             null,
					             tokens.size(),
					             funToAdd)); // int nativeIdx
					  expPos++;
					  break;
		    	  } else if ( '<' == humanExpr.charAt(expPos) || 
		    			      '>' == humanExpr.charAt(expPos) ) {
		    		  
		    		  op_syn_type = OperatorSyntaxType.E_two;
		    		  op_prio = OperatorPriority.E_lev2;
		    		  
		    		  if ( '<' == humanExpr.charAt(expPos) ) {
		    			  funToAdd = new FLt();
		    			  step_type = TypeStep.E_lt;
		    		  } else {
		    			  funToAdd = new FGt();
		    			  step_type = TypeStep.E_gt;
		    		  }
		    	  } else {
		    		  op_syn_type = OperatorSyntaxType.E_one;
		    		  op_prio = OperatorPriority.E_lev0;
		    		  step_type = TypeStep.E_not;
		    		  funToAdd = new FNot();
		    	  }

				  tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)), // String tokenName, 
				             TypeToken.E_op, // TypeToken typeToken, 
				             op_syn_type, // OperatorSyntaxType opeartorSyntaxType,
				             op_prio, // OperatorPriority operatorPriority, 
				             0, // int idxPartOpe, 
				             ValueType.E_nat, // ValueType valueType,
				             step_type, // TypeStep typeStep, 
				             0, // int stepRef,
               		         TypeData.E_string,
				             null,
				             tokens.size(),
				             funToAdd)); // int nativeIdx		    	  
				  break;
		      }
		      case '|':
		      {
		    	  if ( expPos + 1 < humanExpr.length() && '|' == humanExpr.charAt(expPos + 1) ) {
		    		  funToAdd = new FConc();
					  tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos) + "|"), // String tokenName, 
					             TypeToken.E_op, // TypeToken typeToken, 
					             OperatorSyntaxType.E_two, // OperatorSyntaxType opeartorSyntaxType,
					             OperatorPriority.E_lev1, // OperatorPriority operatorPriority, 
					             0, // int idxPartOpe, 
					             ValueType.E_nat, // ValueType valueType,
					             TypeStep.E_conc, // TypeStep typeStep, 
					             0, // int stepRef,
                 		         TypeData.E_string,
					             null,
					             tokens.size(),
					             funToAdd)); // int nativeIdx				    		  
					  expPos++;
		    	  } else {
		    		  funToAdd = new FOr(); 
					  tokens.add(new Token<Object>(String.valueOf(humanExpr.charAt(expPos)), // String tokenName, 
					             TypeToken.E_op, // TypeToken typeToken, 
					             OperatorSyntaxType.E_two, // OperatorSyntaxType opeartorSyntaxType,
					             OperatorPriority.E_lev4, // OperatorPriority operatorPriority, 
					             0, // int idxPartOpe, 
					             ValueType.E_nat, // ValueType valueType,
					             TypeStep.E_or, // TypeStep typeStep, 
					             0, // int stepRef,
                 		         TypeData.E_string,
					             null,
					             tokens.size(),
					             funToAdd
					             )); 		    		  
		    	  }
		    	  
				  break;
		      }
		      default:
		    	  Logger.error("Error in GetToken special character " + humanExpr.charAt(expPos) + " not recognized.");
		    	  return -1;
			}
			
			//Logger.debug("Esco con valore :" + (expPos + 1));

	    	return ++expPos;
		}
		
		// Variable or Operator or Function

		if ( Character.isAlphabetic(humanExpr.charAt(expPos)) ) {
			int idx;
			String funOpeFunction;
			for(idx=expPos+1;idx<humanExpr.length();idx++) {
				
				if ( ! ( Character.isLetterOrDigit(humanExpr.charAt(idx)) || 
					   ( '_' == humanExpr.charAt(idx) || ( '.' == humanExpr.charAt(idx) ) ) ) )  {
					break;
				}
			}
			
			funOpeFunction = humanExpr.substring(expPos, idx);
			
			Logger.debug("Funzione : (" + funOpeFunction + ')');
			
			if ( Functions.names.containsKey(funOpeFunction) ) {
				
				Function function = Functions.names.get(funOpeFunction);
				
				if ( function.getOperatorSyntaxType() == OperatorSyntaxType.E_two ||
				     function.getOperatorSyntaxType() == OperatorSyntaxType.E_three ||
					 function.getOperatorSyntaxType() == OperatorSyntaxType.E_four ) {
					function.checkBefore(humanExpr.substring(0,expPos+humanExpr.substring(expPos, idx).length()),expPos);
				}
				tokens.add(new Token<Object>(function.getName(),
						                     function.getTypeToken(),
						                     function.getOperatorSyntaxType(),
						                     function.getOperatorPriority(),
						                     function.getIdxPartOpe(),
						                     function.getValueType(),
						                     function.getTypeStep(),
						                     function.getStepRef(),
						                     function.getTypeData(),
						                     null,
						                     tokens.size(),
						                     function));
			} else {
				  int idxV = 0;
				  for(idxV=0;idxV<variables.size();idxV++) {
					  if ( variables.get(idxV).getName().compareTo(funOpeFunction) == 0 ) break;
				  }
				  
				  if ( idxV == variables.size() ) {
					  Logger.error("Key Word [" + funOpeFunction + "] isn't a variable or a function.");
					  idx = -1;
				  } else {
					  
				      tokens.add(new Token<Object>(funOpeFunction, // String tokenName, 
				             TypeToken.E_value, // TypeToken typeToken, 
				             OperatorSyntaxType.E_fun, // OperatorSyntaxType opeartorSyntaxType,
				             OperatorPriority.E_lev0, // OperatorPriority operatorPriority, 
				             0, // int idxPartOpe, 
				             ValueType.E_nat, // ValueType valueType,
				             TypeStep.E_variable, // TypeStep typeStep, 
				             idxV, // int stepRef,
				             variables.get(idxV).getTypeVariable(),
				             null,
				             tokens.size(),
				             new FVariable(funOpeFunction)
				             ));						
				  }
			}
			
	    	return idx;
		}
		
		return 1;
	}
	
	/**
	 * Function to tokenize expression
	 * 
	 * @return boolean (true if is ok or false if is nok)
	 */
	
	private boolean tokenize() {
		boolean ret = true;
		int expPos = 0;
	    // Token Left Limit 
	    tokens.add(new Token<Object>(TypeToken.E_lim,0));	
	    
	    while ( true ) {
	    	//Logger.debug("expPos : " + expPos);
	    	int retGetToken = getToken(expPos);
	    	if ( retGetToken < 0 ) {
	    		ret = false;
	    		break;
	    	} else {
	    		if (retGetToken == 0 ) {
	    			break;
	    		}
	    		//Logger.debug("Valore uscita retGetToken : " + retGetToken);
	    		expPos = retGetToken;
	    	}
	    	
	    	if ( expPos >= humanExpr.length() ) break;
	    }
	
	    // Token Right Limit 
	    tokens.add(new Token<Object>(TypeToken.E_lim,tokens.size()));		
	    natTokens = new ArrayList<Token<?>>(tokens);
	    
		return ret;
	}
	
	/**
	 * Function to simplify expression divided in tokens 
	 * 
	 * @return boolean (true if is ok or false if is nok)
	 */
	
	private boolean initSimplify() {
		boolean ret = true;
		
		int idx;
		int idy;
		boolean flag_found = false;
		
		//Logger.debug("YYYYY initSimplify");
		
		for(idx=0;idx<tokens.size();idx++) {
			flag_found = false;
			// If type token is a value
			if ( tokens.get(idx).getTypeToken() == TypeToken.E_value ) {
			   for(idy=0;idy<expBin.getStep().size();idy++) {
				   // If step type different jump to next step
				   if ( tokens.get(idx).getTypeStep() != expBin.getStep().get(idy).getTypeStep() ) continue;
				   // If data type is different jump to next step
				   if ( tokens.get(idx).getTypeData() != expBin.getStep().get(idy).getResType() ) continue;
				   // If constant
				   if ( tokens.get(idx).getTypeStep() == TypeStep.E_constant ) {
					   // If value is different jump to next step
					   if ( tokens.get(idx).getData().getValue() != expBin.getStep().get(idy).getData().getValue() ) continue;
					   
					   // I found it prev
					   
					   tokens.get(idx).setTokenName("R");
					   tokens.get(idx).setTypeToken(TypeToken.E_value);
					   tokens.get(idx).setValueType(ValueType.E_res);
					   tokens.get(idx).setStepRef(idy);
					   
					   flag_found = true;
					   break;
				   }
				   
				   // If variable
				   if ( tokens.get(idx).getTypeStep() == TypeStep.E_variable ) {
					   
					   if ( tokens.get(idx).getStepRef() != expBin.getStep().get(idy).getOpnd().get(0) ) continue;

					   // I found it prev
					   
					   tokens.get(idx).setTokenName("R");
					   tokens.get(idx).setTypeToken(TypeToken.E_value);
					   tokens.get(idx).setValueType(ValueType.E_res);
					   tokens.get(idx).setStepRef(idy);
//					   Logger.error("DEBUG setto variabile step ref a " + tokens.get(idx).getStepRef() );
//					   tokens.get(idx).setStepRef(tokens.get(idy).getStepRef());
					   flag_found = true;
					   break;
					   
				   }
			   }
			
			   // If I found it prev continue
			   if ( flag_found ) continue;
			   
			   
			   if ( tokens.get(idx).getTypeStep() == TypeStep.E_constant ) {
				   
				   expBin.getStep().add( new Step<Object>(  
						   tokens.get(idx).getTypeStep(),
						   tokens.get(idx).getTypeData(), 
						   tokens.get(idx).getData().getValue(),
						   false,
						   new ArrayList<Integer>(),
						   tokens.get(idx).getNativeIdx(),
						   new FConstant()
						   ));
				} else {
					   expBin.getStep().add( new Step<Object>(  
							   tokens.get(idx).getTypeStep(),
							   tokens.get(idx).getTypeData(), 
							   tokens.get(idx).getData().getValue(),
							   false,
							   new ArrayList<Integer>(),
							   tokens.get(idx).getNativeIdx(),
							   tokens.get(idx).getOperatorSyntaxType() == OperatorSyntaxType.E_funi ? 
							   tokens.get(idx).getFunction() : new FVariable(tokens.get(idx).getTokenName())
							   ));					
					   expBin.getStep().get(expBin.getStep().size()-1).getOpnd().add(tokens.get(idx).getStepRef());
				}
			   
			    tokens.get(idx).setTokenName("R");
			    tokens.get(idx).setTypeToken(TypeToken.E_value);
			    tokens.get(idx).setValueType(ValueType.E_res);
			    tokens.get(idx).setStepRef(expBin.getStep().size()-1);
			}
		}
		
		return ret;
	}
	
	/**
	 * Function to simplify brackets if it is possible 
	 * 
	 * @return 0 if it was simplified or 1 if not
	 */
	
	private int brcSimplify() {
		int idx = 0;
		
		for(idx=0;idx<tokens.size();idx++) {
			// If isn't a open bracket continue
			if ( tokens.get(idx).getTypeToken() != TypeToken.E_lbrc ) continue;
			
			// If prec token is an operator or a function or an operator n continue
			if ( idx > 0 && tokens.get(idx-1).getTypeToken() == TypeToken.E_op &&
				 (( tokens.get(idx-1).getOperatorSyntaxType() == OperatorSyntaxType.E_fun ) ||
				  ( tokens.get(idx-1).getOperatorSyntaxType() == OperatorSyntaxType.E_undef )) ) {
				continue;
			}
			
			if ( (idx+1) < tokens.size() && tokens.get(idx+1).getTypeToken() != TypeToken.E_value ) continue;
			
			if ( (idx+2) < tokens.size() && tokens.get(idx+2).getTypeToken() != TypeToken.E_rbrc ) continue;
			
			if ( idx+2 >= tokens.size() ) return -1;
			
		    tokens.remove(idx);
		    tokens.remove(idx+1);
		    
		    return 0;
		}
	    return 1;
	}
	
	/**
	 * Function to simplify functions if it is possible 
	 * 
	 * @return 0 if it was simplified or 1 if not
	 */
	
	private int funSimplify() {
        int idx = 0;
        boolean is_void = false;
        boolean flag_continue = false;
        int nmb_tok_to_del = 0;
        Step<?> newStep = new Step<Object>();
        
        //Logger.debug("YYYYY funSimplify");
		
		for(idx=0;idx<tokens.size();idx++) {		
			is_void = false;
			nmb_tok_to_del = 0;
			flag_continue = false;
			
			if ( tokens.get(idx).getTypeToken() != TypeToken.E_op ) continue;

			if ( tokens.get(idx).getOperatorSyntaxType() != OperatorSyntaxType.E_fun ) continue;

			newStep.setTypeStep(tokens.get(idx).getTypeStep());
			newStep.setUtil_area(tokens.get(idx).getNativeIdx());
			newStep.setOpnd(new ArrayList<Integer>());
			newStep.setFunction(tokens.get(idx).getFunction());

			if ( (idx+1) < tokens.size() && tokens.get(idx+1).getTypeToken() != TypeToken.E_lbrc ) continue;
			
			nmb_tok_to_del++;

			if ( (idx+2) < tokens.size() && tokens.get(idx+2).getTypeToken() == TypeToken.E_rbrc ) {
				is_void = true;
				nmb_tok_to_del++;
			}
  			while ( !is_void ) {
				if ( (idx+nmb_tok_to_del+1) < tokens.size() && tokens.get(idx+nmb_tok_to_del+1).getTypeToken() != TypeToken.E_value ) {
					flag_continue = true;
					break;
				}
				
				// TODO: Change MAX_NMB_OPND impostare da 255 a altro valore, parametrizzare
				if ( newStep.getOpnd().size() == 255 ) {
					Logger.error("Too operand for function (" + tokens.get(idx).getTokenName() + ") " +
				                 "Token " + (idx + 2));
					Logger.printExpAscii(tokens);
					return -1;
				}
				
				//Logger.error("XXXXX 1 Function " + newStep.getFunction() + " set Opnd number : " + newStep.getOpnd().size() + " value : " + tokens.get(idx+nmb_tok_to_del+1).getStepRef());
				
				newStep.getOpnd().add(tokens.get(idx+nmb_tok_to_del+1).getStepRef());
				nmb_tok_to_del++;

				if ( (idx+nmb_tok_to_del+1) < tokens.size() && tokens.get(idx+nmb_tok_to_del+1).getTypeToken() == TypeToken.E_rbrc ) {
   				   nmb_tok_to_del++;
   				   break;
				}
				
				if ( (idx+nmb_tok_to_del+1) < tokens.size() && tokens.get(idx+nmb_tok_to_del+1).getTypeToken() != TypeToken.E_comma ) {
				   flag_continue = true;
				   break;
				}

				nmb_tok_to_del++;
			}
			
			if ( flag_continue ) continue;
			
			expBin.getStep().add(newStep);
			
			tokens.get(idx).setTokenName("R");
			tokens.get(idx).setTypeToken(TypeToken.E_value);
			tokens.get(idx).setValueType(ValueType.E_res);;
			// Mod size() -1 in size()
			tokens.get(idx).setStepRef(expBin.getStep().size()-1);
			//tokens.get(idx).setStepRef(expBin.getStep().size());
			
			for(int i=0;i<nmb_tok_to_del;i++) tokens.remove(idx+1);
			//for(int i=0;i<idx+nmb_tok_to_del+1;i++) {
			//	if ( i >= tokens.size() ) break;
			//	tokens.remove(i);
		//	}

			return 0;
			
		}
	
		return 1;
	}
	
	/**
	 * Function to simplify chain functions if it is possible 
	 * 
	 * @return 0 if it was simplified or 1 if not
	 */
	
	private int chainSimplify(int chain_syn) {
		int idx;
		int idy;
		int nmb_tok_to_del;
		int start_idx;
		int step_mod_idx;
		int has_right_part = 0;
		boolean flag_continue = false;
		Step<?> newStep = new Step<Object>();
		int rs;
        //Logger.debug("YYYYY chainSimplify");
		
		this.printTokens();
		
		for(idx=0;idx<tokens.size();idx++) {		
		   flag_continue = false;
		   
		   if ( tokens.get(idx).getTypeToken() != TypeToken.E_op ) continue;

		   if ( tokens.get(idx).getOperatorSyntaxType() != chain_syn ) continue;
		   
		   if ( tokens.get(idx).getIdxPartOpe() != 0 ) continue;
		   
		   newStep.setTypeStep(tokens.get(idx).getTypeStep());
		   newStep.setUtil_area(tokens.get(idx).getNativeIdx());
		   newStep.setOpnd(new ArrayList<Integer>());
		   newStep.setFunction(tokens.get(idx).getFunction());
		   
		   if ( tokens.get(idx).getOperatorSyntaxType() != 0 ) {
			   
			   if ( idx > 0 && tokens.get(idx-1).getTypeToken() != TypeToken.E_value ) continue;
			   
			   //Logger.error("XXXXX 2 Analize token " + idx + " Function " + newStep.getFunction() + " set Opnd number : " + newStep.getOpnd().size() + " value : " + tokens.get(idx-1).getStepRef());
			   newStep.getOpnd().add(tokens.get(idx-1).getStepRef());
			   
			   if ( idx > 1 && tokens.get(idx-2).getTypeToken() == TypeToken.E_op ) {
				   
				   if ( tokens.get(idx-2).getOperatorSyntaxType() >= 0 && tokens.get(idx-2).getOperatorSyntaxType() <= OperatorSyntaxType.E_nmb_op_chain_syn_type ) {
					   
					   if ( tokens.get(idx-2).getIdxPartOpe() < (((tokens.get(idx-2).getOperatorSyntaxType() == 0 ? 1 : 0)+tokens.get(idx-2).getOperatorSyntaxType())-1) ) {
						   has_right_part = 1;
					   }
					   
					   if ( has_right_part == 0 ) {
						   
						   rs = tokens.get(idx-2).getOperatorSyntaxType() - tokens.get(idx).getOperatorSyntaxType();
						   
						   if ( rs < 0 ) {
							   continue;
						   }
						   
						   if ( rs == 0 ) {
							   rs = tokens.get(idx-2).getOperatorPriority() - tokens.get(idx).getOperatorPriority();
							   if ( rs <= 0 ) continue;
						   }
					   }
				   }
			   }
		   }
		   
		   for( idy=1; idy<( 2*(( tokens.get(idx).getOperatorSyntaxType() == 0 ? 1 : 0) + tokens.get(idx).getOperatorSyntaxType())); idy++) {
		      if ( idy%2 != 0 ) {
		    	 if ( idx+idy < tokens.size() && tokens.get(idx+idy).getTypeToken() != TypeToken.E_value ) {
		    		 flag_continue = true;
		    		 break;
		    	 }
			     //Logger.error("XXXXX 3 Function " + newStep.getFunction() + " set Opnd number : " + newStep.getOpnd().size() + " value : " + tokens.get(idx+idy).getStepRef());
		    	 newStep.getOpnd().add(tokens.get(idx+idy).getStepRef());
		      } else {
		    	  // If is not an operator continue
		    	  if ( idx+idy < tokens.size() && tokens.get(idx+idy).getTypeToken() != TypeToken.E_op ) {
			    	 flag_continue = true;
			    	 break;	  
		    	  }
		    	  if ( idx+idy < tokens.size() && tokens.get(idx).getOperatorSyntaxType() != tokens.get(idx+idy).getOperatorSyntaxType()  ) {
			    	 flag_continue = true;
			    	 break;	  
		    	  }
		    	  if ( idx+idy < tokens.size() && tokens.get(idx).getTypeStep() != tokens.get(idx+idy).getTypeStep()  ) {
			    	 flag_continue = true;
			    	 break;	  
		    	  }
	 	    	  if ( idx+idy < tokens.size() && (idy/2) != tokens.get(idx+idy).getIdxPartOpe()  ) {
			    	 flag_continue = true;
			    	 break;	  
		    	  }
		    	  
		      }
		   }
		   
		   if ( flag_continue ) {
			   continue;
		   }
		   
    	   if ( idx+idy < tokens.size() && tokens.get(idx+idy).getTypeToken() == TypeToken.E_op  ) {
    		  
    		  if ( tokens.get(idx+idy).getOperatorSyntaxType() != OperatorSyntaxType.E_undef ) {
    			  if ( tokens.get(idx+idy).getIdxPartOpe() == 0 ) {
    				  rs = tokens.get(idx).getOperatorSyntaxType() - tokens.get(idx+idy).getOperatorSyntaxType();
    				  
    				  if ( rs > 0 ) continue;
    				  
    				  if ( rs == 0 ) {
    					  rs = tokens.get(idx).getOperatorPriority() - tokens.get(idx+idy).getOperatorPriority();
    					  
    					  if ( rs > 0 ) continue;
    				  }
    					  
    			  }
    		  }
    	   }
    	   
    	   start_idx = idx-((tokens.get(idx).getOperatorSyntaxType() == 0 ? 1 : 0) == 0 ? 1 : 0)+1;
    	   nmb_tok_to_del = idx+idy-start_idx;
    	   for(int i=0;i<nmb_tok_to_del;i++) tokens.remove(start_idx);
    	   
		   step_mod_idx = idx-(((chain_syn == 0) ? 1 : 0) == 0 ? 1 : 0);
		   
		   tokens.get(step_mod_idx).setTokenName("R");
		   tokens.get(step_mod_idx).setTypeToken(TypeToken.E_value);
		   tokens.get(step_mod_idx).setValueType(ValueType.E_res);
		   tokens.get(step_mod_idx).setStepRef(expBin.getStep().size());
		   
		   expBin.getStep().add(newStep);
		   
		   return 0;
		}
	    return 1;	
	}

	/**
	 * Function to simplify undef functions if it is possible 
	 * 
	 * @return 0 if it was simplified or 1 if not
	 */
	
	private int undefSimplify() {
		int  idx;
		int  nmb_tok_to_del;
		boolean flag_continue = false;
		boolean has_right_part = false;
		  
		Step<?> newStep = new Step<Object>();
		
		for( idx=0; idx<tokens.size(); idx++) {
			
			//Logger.debug("Dentro undefSimplify token : " + idx + " type token : " + tokens.get(idx).getTypeToken());
			
			nmb_tok_to_del = 0;
			flag_continue = false;
			has_right_part = false;
			
			if ( tokens.get(idx).getTypeToken() != TypeToken.E_op ) continue;

			if ( tokens.get(idx).getOperatorSyntaxType() != OperatorSyntaxType.E_undef ) continue;
			
			newStep.setTypeStep(tokens.get(idx).getTypeStep());
			newStep.setUtil_area(tokens.get(idx).getNativeIdx());
			newStep.setFunction(tokens.get(idx).getFunction());
			
			nmb_tok_to_del = 1;
			
			if ( (idx - 1) >= 0 && tokens.get(idx-1).getTypeToken() != TypeToken.E_value ) continue;
		
			if ( (idx - 1) >= 0 ) {
		       //Logger.error("XXXXX 4 Function " + newStep.getFunction() + " set Opnd number : " + newStep.getOpnd().size() + " value : " + tokens.get(idx-1).getStepRef());
   			   newStep.getOpnd().add(tokens.get(idx-1).getStepRef());
			}
			
			if ( (idx - 2) >= 0 &&
				 tokens.get(idx-2).getTypeToken() == TypeToken.E_op &&
				 ((tokens.get(idx-2).getOperatorSyntaxType() >= 0) &&
				  (tokens.get(idx-2).getOperatorSyntaxType() < OperatorSyntaxType.E_nmb_op_chain_syn_type))		 
		  	   ) {
				 if ( tokens.get(idx-2).getIdxPartOpe() < (((tokens.get(idx-2).getOperatorSyntaxType()==0?1:0)+tokens.get(idx-2).getOperatorSyntaxType())-1) ) {
					 has_right_part = true;
				 }
				 
				 if ( !has_right_part ) continue;
			}
			
			if ( (idx + 1) < tokens.size() && tokens.get(idx+1).getTypeToken() != TypeToken.E_lbrc ) continue;
			
			nmb_tok_to_del++;
			
			while(true) {
				
				if (idx+nmb_tok_to_del < tokens.size() && tokens.get(idx+nmb_tok_to_del).getTypeToken() != TypeToken.E_value ) {
					flag_continue = true;
					break;
				}
				
				if ( newStep.getOpnd().size() == 255 ) {
					Logger.error("Too operand for function (" + tokens.get(idx).getTokenName() + ") " +
				                 "Token " + (idx + 2));
					Logger.printExpAscii(tokens);
					return -1;
				}
				
		        //Logger.error("XXXXX 5 Function " + newStep.getFunction() + " set Opnd number : " + newStep.getOpnd().size() + " value : " + tokens.get(idx+nmb_tok_to_del).getStepRef());
				newStep.getOpnd().add(tokens.get(idx+nmb_tok_to_del).getStepRef());
				
				nmb_tok_to_del++;
				
				if ( idx+nmb_tok_to_del < tokens.size() && tokens.get(idx+nmb_tok_to_del).getTypeToken() == TypeToken.E_rbrc ) {
					nmb_tok_to_del++;
					break;
				}
				if ( idx+nmb_tok_to_del < tokens.size() && tokens.get(idx+nmb_tok_to_del).getTypeToken() != TypeToken.E_comma ) {
					flag_continue = true;
					break;
				}
				
				nmb_tok_to_del++;
			}
			
			if ( flag_continue ) continue;
			
			expBin.getStep().add(newStep);
			
			if ( (idx-1) >= 0 ) {
				tokens.get(idx-1).setTokenName("R");
				tokens.get(idx-1).setTypeToken(TypeToken.E_value);
				tokens.get(idx-1).setValueType(ValueType.E_res);
				tokens.get(idx-1).setStepRef(expBin.getStep().size()-1);
			}
			
			//Logger.debug("nmb_tok_to_del : " + nmb_tok_to_del);
			
			for(int i=0;i<nmb_tok_to_del;i++) tokens.remove(idx);
			
			return 0;
		}
		
		return 1;
	}
	
	/**
	 * Function to call all simplify methods 
	 * 
	 * @return 0 if it was simplified or 1 if not
	 */	

	private int simplify() {
		int rs = 0;

		// Simplify the brackets
		rs = brcSimplify();
		
		if ( rs < 0 ) {
   		   Logger.error("brcSimplify Problem.");
   		   return rs;
		}
		
		if ( rs == 0 ) {
			//Logger.debug("brcSimplify fatta");
			return rs;
		}
		
		// Simplify the Function
		rs = funSimplify();
		
		if ( rs < 0 ) {
   		   Logger.error("funSimplify Problem.");
   		   return rs;
		}
		
		if ( rs == 0 ) {
			//Logger.debug("funSimplify fatta");
			return rs;
		}
		
		// Simplify Operators with Chain Syntax 
		for (int idx_chain_syn=-1; idx_chain_syn < OperatorSyntaxType.E_nmb_op_chain_syn_type; idx_chain_syn++) {
			rs = chainSimplify( idx_chain_syn );
			
			if ( rs < 0 ) {
      		   Logger.error("chainSimplify Problem.");
     		   return rs;
			}

			//if ( rs == 0 ) Logger.debug("chainSimplify fatta");
			
			if ( rs == 1 ) continue;
			
			return 0;
		}
		
		rs = undefSimplify();
		
		//Logger.debug("rs undefSimplify : " + rs);
		
		if ( rs < 0 ) {
   		   Logger.error("undefSimplify Problem.");
  		   return rs;
		}
		
		if ( rs == 0 ) {
   		    //Logger.error("undefSimplify done.");
			return 0;
		}

		return 1;
	}
	
	/**
	 * Function to execute compiled Expression 
	 * 
	 * @return always 0 
	 */	
	
	public int execExpr(ArrayList<Variable<?>> variables) {
		
		expBin.setVariables(variables);
		int idx = expBin.getStep().size()-1;
		if ( idx < 0 ) idx = 0;
		if ( !expBin.getStep().get(idx).getFunction().exec(expBin, idx) ) return -1;
		
		// Set type result
		
		if ( Utility.isBoolean(expBin.getStep().get(idx).getData().getValue()) ) {
			expBin.getStep().get(idx).getData().setTypeData(TypeData.E_boolean);	
		} else if ( Utility.isDate(expBin.getStep().get(idx).getData().getValue()) ) {
			expBin.getStep().get(idx).getData().setTypeData(TypeData.E_date);	
		} else if ( Utility.isString(expBin.getStep().get(idx).getData().getValue()) ) {
			expBin.getStep().get(idx).getData().setTypeData(TypeData.E_string);	
		} else if ( Utility.isInteger(expBin.getStep().get(idx).getData().getValue()) ) {
			expBin.getStep().get(idx).getData().setTypeData(TypeData.E_int);	
		} else if ( Utility.isDouble(expBin.getStep().get(idx).getData().getValue()) ) {
			expBin.getStep().get(idx).getData().setTypeData(TypeData.E_double);	
		}
		
		// Set is null
		
		if ( expBin.getStep().get(idx).getData().getValue() == null ||
			 expBin.getStep().get(idx).getData().getValue() == "" ) {
		   expBin.getStep().get(idx).getData().setNull(true);	
		} else {
		   expBin.getStep().get(idx).getData().setNull(false);	
		}
		
		return 0;
	}
	
	/**
	 * Function to get result execution of a compiled Expression 
	 * 
	 * @return DataValue element
	 */	
	
	public DataValue<?> getResult() {
		int idx = expBin.getStep().size()-1;
		if ( idx < 0 ) idx = 0;
		return expBin.getStep().get(idx).getData();
	}

	/**
	 * Method to check tokens generated
	 * 
	 * @return 0 if it's ok different value if nok
	 */
	
	private int checkExpr() {
		
		if ( tokens.size() != 3 ) {
			return -1;
		}

		if ( tokens.get(0).getTypeToken() != TypeToken.E_lim ) {
			return -2;
		}

		if ( tokens.get(1).getTypeToken() != TypeToken.E_value ) {
			return -3;
		}

		if ( tokens.get(2).getTypeToken() != TypeToken.E_lim ) {
			return -4;
		}
		
		return 0;
	}
	
	/**
	 * This method permits to show in what token there is a problem
	 * 
	 * @return 0 if it's ok different value if nok
	 */
	
	private int resDataTypeDefinition() {	
		  int idx;
		  int idy;
		  int arrow = 0;
		  String log_msg_tail = "";
		  
		  for( idx = 0; idx < expBin.getStep().size(); idx++) {
			  
			  Logger.debug(expBin.getStep().get(idx).getTypeStep());
			  
			  if ( !expBin.getStep().get(idx).getFunction().check(expBin, idx) ) {
				 
				 log_msg_tail = log_msg_tail + natTokens.get(1).getTokenName();
				 for( idy=2;idy < natTokens.size()-1; idy++) {
					 if ( idy == expBin.getStep().get(idx).getUtil_area() ) {
						 arrow = log_msg_tail.length();
					 }
					 log_msg_tail = log_msg_tail + natTokens.get(idy).getTokenName() + " ";
				 }
				  
				 log_msg_tail = log_msg_tail + "\n" + new String(new char[arrow]).replace('\0', ' ') + "^";
				 
				 Logger.error("Anomalous number of operand or operand type for step " + idx + "\n" + log_msg_tail);
				 return -1;
			  }
		  }
		  
		  return 0;
	}
	
	/**
	 * This method check, parse and compile expression
	 * 
	 * @return true if if ok false if nok
	 */
	
    public boolean compExpr() {

    	 boolean rs = true;
    	 int codRit = 0;
    	 
    	 while(true) {
	    	 if ( !initCompiling() ) {
	     		 Logger.error("InitCompiling Problem.");
	    		 rs = false;
	    		 break;
	    	 }
	    	 
	    	 if ( !tokenize() ) {
	    		Logger.error("Tokenize Problem.");
	    		rs = false;
	    		break;
	    	 }
	    	 
	    	 Logger.printExpAscii(tokens);
	    		 
	  		 if ( !initSimplify() ) {
	    		Logger.error("InitSimplify Problem.");
	    		rs = false;
	    		break;
	  		 }

	    	 Logger.printExpAscii(tokens);
	  		 
	  		 while(true) {
    	        codRit = simplify();
    	        
    	        if ( codRit < 0 ) {
   	    		   Logger.error("Simplify Problem.");
   	    		   rs = false;
   	    		   break;
    	        }
    	        
    	        if ( codRit == 1 ) break;
    	        
    	        Logger.printExpAscii(tokens);
    	        
	  		 }
	  		 
	  		 
	  		 break;
         }
    	 
    	 if ( checkExpr() != 0 ) {
     		Logger.error("checkExpr Problem.");
	    	rs = false;
	    	return rs;
    	 }

    	 // Assign variable
    	 expBin.setVariables(variables);
    	 
    	 // Log expBin
   		 Logger.debug("*****************************************************\nExpBin");
    	 for(int i=0; i < expBin.getStep().size();i++) {
    		 Logger.debug("Step " + i);
    		 Logger.debug("TypeStep : " + expBin.getStep().get(i).getTypeStep());
    		 Logger.debug("ResType  : " + expBin.getStep().get(i).getResType());
    		 Logger.debug("Opnd     : " + expBin.getStep().get(i).getOpnd().size());
    		 Logger.debug("UtilArea : " + expBin.getStep().get(i).getUtil_area());
    		 Logger.debug("res type : " + expBin.getStep().get(i).getResType());
    		 Logger.debug("type     : " + expBin.getStep().get(i).getTypeData());
    		 if ( expBin.getStep().get(i).getTypeStep() == TypeStep.E_constant ) Logger.debug("value    : " + expBin.getStep().get(i).getData().getValue());
    		 Logger.debug("------------------------------------------------------------------");
    	 }

    	 if ( resDataTypeDefinition() != 0 ) {
     		Logger.error("resDataTypeDefinition Problem.");
	    	rs = false;
	    	return rs;
    	 }
    	 
    	 return rs;
    	}
    
    
        void printTokens() {
        	Logger.debug("*****************************************************\nTokens");
        	for(int i=0; i < tokens.size();i++) {
    		   Logger.debug("Token        : " + i);
    		   Logger.debug("Token Name   : " + tokens.get(i).getTokenName());
    		   Logger.debug("Token Type   : " + tokens.get(i).getTypeToken());
    		   Logger.debug("Token Syntax : " + tokens.get(i).getOperatorSyntaxType());
    		   Logger.debug("Value Type   : " + tokens.get(i).getValueType());
    		   Logger.debug("Step Ref     : " + tokens.get(i).getStepRef());
    		   Logger.debug("Function     : " + tokens.get(i).getFunction());
    		   Logger.debug("Value        : " + tokens.get(i).getData() != null ? tokens.get(i).getData() : "");
         	   Logger.debug("------------------------------------------------------------------");
        	}
        	
        }
 }
