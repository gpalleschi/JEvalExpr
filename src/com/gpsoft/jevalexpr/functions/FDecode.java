package com.gpsoft.jevalexpr.functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.gpsoft.jevalexpr.DataValue;
import com.gpsoft.jevalexpr.ExpBin;
import com.gpsoft.jevalexpr.OperatorPriority;
import com.gpsoft.jevalexpr.OperatorSyntaxType;
import com.gpsoft.jevalexpr.Step;
import com.gpsoft.jevalexpr.TypeData;
import com.gpsoft.jevalexpr.TypeStep;
import com.gpsoft.jevalexpr.TypeToken;
import com.gpsoft.jevalexpr.Utility;
import com.gpsoft.jevalexpr.ValueType;
import com.gpsoft.jevalexpr.log.Logger;

public class FDecode extends Function{

	public FDecode(String name) {
		super();
		
		this.name = name;
		this.typeToken = TypeToken.E_op;
		this.operatorSyntaxType = OperatorSyntaxType.E_fun;
		this.operatorPriority = OperatorPriority.E_lev0;
		this.idxPartOpe = 0;
		this.valueType = ValueType.E_nat;
		this.typeStep =TypeStep.E_decode; 
		this.stepRef = 0;
		this.typeData = TypeData.E_string;
		
	}

	public boolean check(ExpBin<?> expBin, int idxStep) {

		Step<?> step = expBin.getStep().get(idxStep);
		int idxOpd;
		int idxOpd2;
		int idxOpd3;
		
		if (step.getOpnd().size() < 3 ) {
			Logger.error("Function decode work with at least three arguments not with " + step.getOpnd().size() + ".");
			return false;
		}
		
		idxOpd = step.getOpnd().get(0);
		idxOpd2 = step.getOpnd().get(1);
		idxOpd3 = step.getOpnd().get(2);
		
		TypeData firstArgTD = expBin.getStep().get(idxOpd).getResType();
		TypeData rifType = expBin.getStep().get(idxOpd2).getResType();
		TypeData decodeType = expBin.getStep().get(idxOpd3).getResType();
		
		if ( firstArgTD != rifType ) {
			Logger.error("function decode first argument and second and even indexed arguments must be of same type.");
			return false;		   	
		}
		
		int i;
		
		for(i=3;i<step.getOpnd().size();i++) {
			
			if ( i%2 != 0 && i != step.getOpnd().size()-1 ) {
				if ( firstArgTD != expBin.getStep().get(i).getResType() ) {
					Logger.error("function decode first argument and odd indexed arguments must be of same type.");
					return false;		   	
				}
			} else {
				if ( decodeType != expBin.getStep().get(i).getResType() ) {
					Logger.error("function decode even indexed arguments must be of same type.");
					return false;		   	
				}
			}
		}
		
		if ( i%2 == 0 )

		expBin.getStep().get(idxStep).setResType(decodeType);
		
		return true;
	}
	
	private boolean setDefault(TypeData decodeType, ExpBin<?> expBin, int idxStep) {
		if ( decodeType == TypeData.E_boolean ) {
    		expBin.getStep().get(idxStep).setData(new DataValue<Boolean>(false));
    		expBin.getStep().get(idxStep).setNull(false);
		} else if ( decodeType == TypeData.E_int ) {
    		expBin.getStep().get(idxStep).setData(new DataValue<Integer>(0));
    		expBin.getStep().get(idxStep).setNull(false);
		} else if ( decodeType == TypeData.E_double ) {
    		expBin.getStep().get(idxStep).setNull(false);
    		expBin.getStep().get(idxStep).setData(new DataValue<Double>(0.0));
		} else if ( decodeType == TypeData.E_string ) {
    		expBin.getStep().get(idxStep).setData(new DataValue<String>(""));
    		expBin.getStep().get(idxStep).setNull(true);
		} else if ( decodeType == TypeData.E_date ) {
			LocalDate date = LocalDate.parse("1970-01-01");
    		expBin.getStep().get(idxStep).setData(new DataValue<LocalDate>(date));
    		expBin.getStep().get(idxStep).setNull(false);
		} else {		
			return false;
		}
		return true;
	}
	
	private boolean compareData(DataValue<?> data1, DataValue<?> data2 ) {
	
		// Impossible because was checked 
		if ( data1.getTypeData() != data2.getTypeData() ) return false;
		
		if ( data1.getTypeData() == TypeData.E_string ) {
			String value1 = (String)data1.getValue();
			String value2 = (String)data2.getValue();
			
			if ( value1.compareTo(value2) == 0 ) {
				return true;
			} else {
				return false;
			}
		} else if ( data1.getTypeData() == TypeData.E_string ) {
			int value1 = (Integer)data1.getValue();
			int value2 = (Integer)data2.getValue();
			
			if ( value1 == value2 ) {
				return true;
			} else {
				return false;
			}
		} else if ( data1.getTypeData() == TypeData.E_boolean ) {
			Boolean value1 = (Boolean)data1.getValue();
			Boolean value2 = (Boolean)data2.getValue();
			
			if ( value1 == value2 ) {
				return true;
			} else {
				return false;
			}
			
		} else if ( data1.getTypeData() == TypeData.E_double ) {
			Double value1 = (Double)data1.getValue();
			Double value2 = (Double)data2.getValue();
			if ( value1 == value2 ) {
				return true;
			} else {
				return false;
			}
		} else if ( data1.getTypeData() == TypeData.E_date ) {
			
			if ( data1.getValue().getClass().getName().compareTo("java.time.LocalDateTime") == 0 &&
			     data2.getValue().getClass().getName().compareTo("java.time.LocalDateTime") == 0 ) {
				LocalDateTime localDateTime1 = (LocalDateTime)data1.getValue();
				LocalDateTime localDateTime2 = (LocalDateTime)data2.getValue();
				
				return localDateTime1.equals(localDateTime2);
			} else if ( data1.getValue().getClass().getName().compareTo("java.time.LocalDate") == 0 &&
				        data2.getValue().getClass().getName().compareTo("java.time.LocalDate") == 0 ) {
						LocalDate localDate1 = (LocalDate)data1.getValue();
						LocalDate localDate2 = (LocalDate)data2.getValue();
						return localDate1.equals(localDate2);				
			} else if ( data1.getValue().getClass().getName().compareTo("java.time.LocalTime") == 0 &&
				        data2.getValue().getClass().getName().compareTo("java.time.LocalTime") == 0 ) {
						LocalTime localTime1 = (LocalTime)data1.getValue();
						LocalTime localTime2 = (LocalTime)data2.getValue();
						return localTime1.equals(localTime2);				
			} else {
				return false;
			}
		} else {
		  return false;
		}
	}

	private boolean setResult(ExpBin<?> expBin, int idxStep, DataValue<?> data) {
		
		if ( Utility.isString(data.getValue()) ) {
			expBin.getStep().get(idxStep).setData(new DataValue<String>((String)data.getValue()));
		} else if ( Utility.isInteger(data.getValue()) ) {
			expBin.getStep().get(idxStep).setData(new DataValue<Integer>((Integer)data.getValue()));
		} else if ( Utility.isDouble(data.getValue()) ) {
			expBin.getStep().get(idxStep).setData(new DataValue<Double>((Double)data.getValue()));
		} else if ( Utility.isBoolean(data.getValue()) ) {
			expBin.getStep().get(idxStep).setData(new DataValue<Boolean>((Boolean)data.getValue()));
		} else if ( Utility.isDate(data.getValue()) ) {
			if ( data.getValue().getClass().getName().compareTo("java.time.LocalDateTime") == 0 ) {
				expBin.getStep().get(idxStep).setData(new DataValue<LocalDateTime>((LocalDateTime)data.getValue()));
			} else if ( data.getValue().getClass().getName().compareTo("java.time.LocalDate") == 0 ) {
						expBin.getStep().get(idxStep).setData(new DataValue<LocalDate>((LocalDate)data.getValue()));
			} else if ( data.getValue().getClass().getName().compareTo("java.time.LocalTime") == 0 ) {
						expBin.getStep().get(idxStep).setData(new DataValue<LocalTime>((LocalTime)data.getValue()));
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	
		expBin.getStep().get(idxStep).setNull(false);
		return true;
	}
		

	public boolean exec(ExpBin<?> expBin, int idxStep) {
		
		Logger.debug("In Exec FDecode");	
		
		Step<?> step = expBin.getStep().get(idxStep);
		
		int idxOpd1 = step.getOpnd().get(0);
		int idxOpdOdd = step.getOpnd().get(1);;
		int idxOpdEven = step.getOpnd().get(2);;
		
		TypeData decodeType = expBin.getStep().get(idxOpdEven).getResType();
		
		if ( !expBin.getStep().get(idxOpd1).getFunction().exec(expBin, idxOpd1) ) return false;
	
		if ( expBin.getStep().get(idxOpd1).isNull() ) {
			if ( !setDefault(decodeType,expBin, idxStep) ) {
				Logger.error("Binary Expression Corrupted");
				return false;
			}
			return true;
		}

		int i=1;
		for(i=1;i<step.getOpnd().size();i++) {
			
			if ( i%2 != 0 && i != step.getOpnd().size()-1 ) {
				
				idxOpdOdd = i;
		        idxOpdEven = ++i;
				
				if ( !expBin.getStep().get(idxOpdOdd).getFunction().exec(expBin, idxOpdOdd) ) return false;
				if ( !expBin.getStep().get(idxOpdEven).getFunction().exec(expBin, idxOpdOdd) ) return false;

				if ( expBin.getStep().get(idxOpdOdd).isNull() ) {
					if ( !setDefault(decodeType,expBin, idxStep) ) {
						Logger.error("Binary Expression Corrupted");
						return false;
					}
					return true;
				} else {
					if ( expBin.getStep().get(idxOpdEven).isNull() ) {
						continue;
					}
					
					if ( compareData(expBin.getStep().get(idxOpdOdd).getData(), expBin.getStep().get(idxOpdEven).getData()) ) {
						
						if ( !setResult(expBin, idxStep, expBin.getStep().get(idxOpdEven).getData()) ) return false;
						break;
						
					} else {
						continue;
					}
				}
				
			} else {
				
				// Default value last argument in decode function
				if ( i == step.getOpnd().size()-1 ) {
				
					if ( !expBin.getStep().get(i).getFunction().exec(expBin, i) ) return false;

					if ( expBin.getStep().get(i).isNull() ) {
						if ( !setDefault(decodeType,expBin, idxStep) ) {
							Logger.error("Binary Expression Corrupted");
							return false;
						}
					}
					break;
				}
				
			}
			
		}
		
		
		// If don't match
		if ( i == step.getOpnd().size() ) {
			
			int idxOpd;
			
			// Check if there is a default 
			if ( step.getOpnd().size()%2 == 0 ) {
				idxOpd = step.getOpnd().get(step.getOpnd().size()-1);
			} else {
				idxOpd = step.getOpnd().get(0);
			}
			Logger.error("idxOpd : " + idxOpd);
			if ( !setResult(expBin, idxStep, expBin.getStep().get(idxOpd).getData()) ) return false;	
			
		}

		return true;
	}
}