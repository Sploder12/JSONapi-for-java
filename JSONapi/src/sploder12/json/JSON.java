package sploder12.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JSON {
		public JSON(){
			InputStream mapfile;
			try {
				mapfile = new FileInputStream("Enemies.json");
				String ses =convertToString(mapfile);
				int beneb = locateStringEnd(ses,"hp");
				Boolean semes = getBoolValueOfDict(ses,beneb);
				System.out.println(semes);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[]args){
			new JSON();
		}
	
		public String convertToString(InputStream jsonfile){
			boolean reading = true;
			String converted = "Oops something went wrong!";
			int index = 0;
			char[] stringchar = new char[500000]; //can only support 250kb files MAX
			for(int x = 0;x<stringchar.length;x++){
				stringchar[x] = 'X';
			}
			try{
				while(reading){ 
					int z = jsonfile.read();
					if(z > -1){
						stringchar[index] = (char)z;
						index++;
					}else{
						reading = false;	
					}
				}
				char[] parse = new char[index];
				for(int x = 0; x < index; x++){
					parse[x] = stringchar[x]; 
				}
				String b = new String(stringchar);
				converted = b;
			}catch(Exception e){
				System.out.println("Failed To Convert To String("+jsonfile+")");
			}
			return converted;
			
		}
		
		public int locateStringStart(String string, String wantedString){
			CharSequence seq = wantedString; 
		    boolean bool = string.contains(seq);
		    if(bool){
		    	int location = string.indexOf(wantedString);
		    	return location;
		    }else{
		    	System.out.println("Requested String Cannot Be Found");
		    	return -1;
		    }
		}
		
		public int locateStringStart(String string, String wantedString, int afterIndex){
			CharSequence seq = wantedString; 
		    boolean bool = string.contains(seq);
		    if(bool){
		    	int location = string.indexOf(wantedString,afterIndex);
		    	return location;
		    }else{
		    	System.out.println("Requested String Cannot Be Found");
		    	return -1;
		    }
		}
		
		public int locateStringEnd(String string, String wantedString, int afterIndex){
			CharSequence seq = wantedString; 
		    boolean bool = string.contains(seq);
		    if(bool){
		    	int location = string.indexOf(wantedString,afterIndex);
		    	location += wantedString.length();
		    	return location;
		    }else{
		    	System.out.println("Requested String Cannot Be Found");
		    	return -1;
		    }
		}
		
		public int locateStringEnd(String string, String wantedString){
			CharSequence seq = wantedString; 
		    boolean bool = string.contains(seq);
		    if(bool){
		    	int location = string.indexOf(wantedString);
		    	location += wantedString.length();
		    	return location;
		    }else{
		    	System.out.println("Requested String Cannot Be Found");
		    	return -1;
		    }
		}
		
		public String getValueOfDict(String jsonFileString, int indexOfEndOfString){
			return ParseNumDict(jsonFileString, indexOfEndOfString);
		}
		
		public int getIntValueOfDict(String jsonFileString, int indexOfEndOfString){
			return Integer.parseInt(ParseNumDict(jsonFileString, indexOfEndOfString));
		}
		
		public float getFloatValueOfDict(String jsonFileString, int indexOfEndOfString){
			return Float.parseFloat(ParseNumDict(jsonFileString, indexOfEndOfString));
		}
		
		private String ParseNumDict(String jsonFileString, int indexOfEndOfString){
			int indexof = 0;
			boolean reading = true;
			char potato[] = new char[45];
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2 + indexof);
				if(check != ' ' && check != ':' && check != '{' && check != '}' && check != '[' && check != ']' && check != '"' && check != ','){
					potato[indexof] = check;
					indexof++;
				}else if (indexof > 0){
					reading = false;
				}else if(indexof == 0 && (check == ' ' || check == '"')){
					indexOfEndOfString++;
				}
			}
			char epic[] = new char[indexof];
			for(int x= 0; x < indexof;x++){
				epic[x] = potato[x];
			}
			String b = new String(epic);
			return b;
		}
	
		public double getDoubleValueOfDict(String jsonFileString, int indexOfEndOfString){
			return Double.parseDouble(ParseNumDict(jsonFileString, indexOfEndOfString));
		}
		
		public boolean getBoolValueOfDict(String jsonFileString, int indexOfEndOfString){
			boolean value = false;
			String check = ParseNumDict(jsonFileString, indexOfEndOfString);
			if(!check.equalsIgnoreCase("true") && !check.equalsIgnoreCase("false")){
				System.out.println("Returned False Because Value Couldn't Be Parsed To Boolean");
			}else{
				value =Boolean.parseBoolean(ParseNumDict(jsonFileString, indexOfEndOfString));
			}
			return value;
		}
		
		public char getCharValueOfDict(String jsonFileString, int indexOfEndOfString){
			boolean reading = true;
			char potato = '~';
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2);
				if(check != ' ' && check != ':' && check != '{' && check != '}' && check != '[' && check != ']' && check != '"'){
					potato = check;
					reading = false;
				}else{
					indexOfEndOfString++;
				}
			}
			return potato;
		}
		
		public String[] getString1DArray(String jsonFileString, int indexOfEndOfString){
			return parseArray(jsonFileString, indexOfEndOfString);
		}
		
		private String[] parseArray(String jsonFileString, int indexOfEndOfString){
			boolean reading = true;
			int Stringnum = 0;
			int index = 0;
			char[][] chararray = new char[50][45]; //maximum size is 50 Strings of 45 characters			
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2 + index);
				if(check != '}' && check != '[' && check != ']'){
					//System.out.println(check);
					if(check == ','){
						Stringnum++;
						indexOfEndOfString +=(index+1);			
						index = 0;
					}else if(check == '{'){
						indexOfEndOfString++;
					}else{
						chararray[Stringnum][index] = check;
						index++;
					}
				}else if(check == '}'){
					reading = false;
				}
			}
			String[] returning = new String[Stringnum+1];
			for(int stringcur=0;stringcur <= Stringnum; stringcur++){
				int parselength = 0;
				for(int indexcopy=0; indexcopy < chararray[stringcur].length; indexcopy++){
					if(chararray[stringcur][indexcopy] != 0){
						parselength++;
					}
				}
				char[] parseprep = new char[parselength];
				for(int copy = 0; copy < parselength; copy++){
					parseprep[copy] = chararray[stringcur][copy];
				}
				String parsedstring = new String(parseprep);
				returning[stringcur] = parsedstring;
			}
			return returning;
		}
		
		public int[] getInt1DArray(String jsonFileString, int indexOfEndOfString){
			int[] intarray = new int[parseArray(jsonFileString, indexOfEndOfString).length];
			for(int x = 0; x < intarray.length; x++){ 
				intarray[x] = Integer.parseInt(parseArray(jsonFileString, indexOfEndOfString)[x]);
			}
			return intarray;
		}
		
		
		public double[] getDouble1DArray(String jsonFileString, int indexOfEndOfString){
			double[] doublearray = new double[parseArray(jsonFileString, indexOfEndOfString).length]; //using .length because null values can't parse
			for(int x = 0; x < doublearray.length; x++){ 
				doublearray[x] = Double.parseDouble(parseArray(jsonFileString, indexOfEndOfString)[x]);
			}
			return doublearray;
		}
		
		public boolean[] getBool1DArray(String jsonFileString, int indexOfEndOfString){
			boolean[] boolarray = new boolean[parseArray(jsonFileString, indexOfEndOfString).length];
			String[] check = new String[parseArray(jsonFileString, indexOfEndOfString).length];
			for(int x = 0; x < boolarray.length; x++){ 
				check[x] = parseArray(jsonFileString, indexOfEndOfString)[x];
				if(!check[x].equalsIgnoreCase("true") || !check[x].equalsIgnoreCase("false")){
					System.out.println("Value In Index "+x+" Is False Because It Could Not Be Parsed Correctly");
				}else{
					boolarray[x] = Boolean.parseBoolean(check[x]);
				}
			}
			return boolarray;
		}
}
