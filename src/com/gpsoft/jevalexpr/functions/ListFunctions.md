# JEvalExpr List Functions

In this file are present all functions configurated in JEvalExpr with examples.  

<hr/>

* [abs](#abs)
* [acos](#acos)
* [and](#and)
* [asin](#asin)
* [atan](#atan)
* [between](#between)
* [ceil](#ceil)
* [cos](#cos)
* [decode](#decode)
* [E](#E)
* [extr_array](#extrarray)
* [in](#in)
* [initcap](#initcap)
* [instr](#instr)
* [is_date](#isdate)
* [is_null](#isnull)
* [is_number](#isnumber)
* [length](#length)
* [like](#like)
* [lower](#lower)
* [lpad](#lpad)
* [ltrim](#trim)
* [max](#max)
* [min](#min)
* [mod](#mod)
* [not](#not)
* [nvl](#nvl)
* [or](#or)
* [PI](#PI)
* [regexp_instr](#regexpinstr)
* [regexp_like](#regexplike)
* [regexp_substr](#regexpsubstr)
* [replace](#replace)
* [reverse](#reverse)
* [round](#round)
* [rpad](#rpad)
* [rtrim](#rtrim)
* [sin](#sin)
* [substr](#substr)
* [sysdate](#sysdate)
* [tan](#tan)
* [to_char](#tochar)
* [to_date](#todate)
* [to_hhmiss](#tohhmiss)
* [to_number](#tonumber)
* [translate](#translate)
* [trim](#trim)
* [trunc](#trunc)
* [upper](#upper)
* [xor](#xor)

<hr/>
<h2 id="abs">abs</h2>
Function to calculate absolute number value.  
#####Syntax
```
abs(x)
```
Where x can be a numeric variable (int or double) or string contains a numeric value.  

#####Result
Result will be an Integer or a Double depends input type.
#####Examples
```
abs(-1)

Result will be : 1 (Integer)
```

<hr/>
<h2 id="acos">acos</h2>
Function to calculate arccosine, function works only with a numeric argument .  

#####Syntax

```
acos(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
acos(0)

Result will be : 1.5707963267948966 (Double) 
```

<hr/>
<h2 id="and">and</h2>
Logic operator and .  

#####Syntax

```
x and y
```
Where x and y can be variables of type Integer or Boolean or functions that returns Integer or Boolean.

#####Result
Result will be a Boolean value.

#####Examples
```
1 and 0

Result will be : false (Boolean) 
```
<hr/>
<h2 id="asin">asin</h2>
Function to calculate arcsine, function works only with a numeric argument .  

#####Syntax

```
asin(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
asin(0)

Result will be : 0.0 (Double) 
```
<hr/>
<h2 id="atan">atan</h2>
Function to calculate arctangent, function works only with a numeric argument .  

#####Syntax

```
atan(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
atan(180)

Result will be : 1.3386902103511544 (Double) 
```
<hr/>
<h2 id="between">between</h2>
Function to check if a value is within a range of values, function works only with numbers (Integer or double).  

#####Syntax

```
x between y and z
```
Where x is the value to check and y and z the range of values.

#####Result
Result will be a Boolean.

#####Examples
```
31 between 12 and 45

Result will be : true (Boolean) 
```
<hr/>
<h2 id="ceil">ceil</h2>
Function to rounds up and returns the smallest integer greater than or equal to a given number, function works only with numbers (Integer or Double).

#####Syntax

```
ceil(x[,y])
```
Where x is the number to round and y is number of digital to trunc.

#####Result
Result will be a number (Integer or Double).

#####Examples
```
ceil(5.345)

Result will be : 6 (Integer) 
```
<hr/>
<h2 id="cos">cos</h2>
Function to calculate cosine, function works only with a numeric argument .  

#####Syntax

```
cos(x)
```
Where x is a number (Integer or Double).

#####Result
Result will be a Double.

#####Examples
```
cos(0)

Result will be : 1.0 (Double) 
```
<hr/>
<h2 id="decode">decode</h2>
Function decode compares a value to each search value one by one. If expr is equal to a search, then returns the corresponding result. If no match is found, then returns, if present default value. If default is omitted, then returns initial value.

#####Syntax

```
decode(x,y1,z1[,y2,z2, .... yn,zn,d])
```
Where x is value to compare and y1 to yn value to compare to x and if equals, z1 to zn are values to return, d is default value if present. Variables can be Numbers (Integer or Duoble), String, Boolean or Date, it's important that x and y1..yn are of the same type, so z1...zn and d.


#####Result
Result will be a number (Double or Integer, String, Boolean or Date).

#####Examples
```
decode(12,1,'A',2,'B',12,'C','D')

Result will be : 'C' (String) 
```
<hr/>
<h2 id="E">E</h2>
E is a constant Euler's Number (2.718281828459045).

#####Syntax

```
E
```
Where x is value to compare and y1 to yn value to compare to x and if equals, z1 to zn are values to return, d is default value if present. Variables can be Numbers (Integer or Duoble), String, Boolean or Date, it's important that x and y1..yn are of the same type, so z1...zn and d.


#####Result
Value is the number Double 2.718281828459045.

#####Examples
```
E

Result will be : 2.718281828459045 (Double) 
```
<hr/>
<h2 id="extrarray">extr_array</h2>
Function extr_array is a function that allows you to extract a substring from a string formed by values ​​separated by a separator character, specifying its position. If can't extract the substring returns null.

#####Syntax

```
extr_array(x,y,z)
```
Where x is a string, y is the position and z is the separator. Position is from 1.


#####Result
Return values is a String.

#####Examples
```
extr_array('1,223,31,4987',2,',')

Result will be : '223' (String) 
```
<hr/>
<h2 id="in">in</h2>
Function in allows to check if a value in present in a group of values specified. Value and compare values can be String, number (Double or Integer), Boolean or Date.

#####Syntax

```
x in (y1,y2,.... yn)
```
Where x is the value to check, y1...yn los values to compare.


#####Result
Return values is Boolean.

#####Examples
```
'pippo' in ('pluto','paperino','minni','pluto')

Result will be : false (Boolean) 
```
<hr/>
<h2 id="initcap">initcap</h2>
Function initcap return an input String, with the first letter of each word in uppercase, all other letters in lowercase.

#####Syntax

```
initcap(x)
```
Where x is the String to transform.


#####Result
Return values is String.

#####Examples
```
initcap('john white')

Result will be : 'John White' (String) 
```
<hr/>
<h2 id="instr">instr</h2>
The instr function searchs string for substring. The search operation is defined as comparing the substring argument with substrings of string of the same length for equality until a match is found or there are no more substrings left. Each consecutive compared substring of string begins one character to the right, function returns position of substring (from 1) if founded or 0.

#####Syntax

```
instr(x,y[,z,k])
```
Where x is the input String, y the substring, z start position .


#####Result
Return values is Integer.

#####Examples
```
instr('aXaaXaaaXaaaaX','X',1,3)

Result will be : 9 (Integer) 
```