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
				int[] semes = getInt1DArray(ses,beneb);
				System.out.println(semes[1]);
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
		
		public int getIntValueOfDict(String jsonFileString, int indexOfEndOfString){
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
				}else if(indexof == 0 && check == ' '){
					indexOfEndOfString++;
				}
			}
			char epic[] = new char[indexof];
			for(int x= 0; x < indexof;x++){
				epic[x] = potato[x];
			}
			String b = new String(epic);
			return Integer.parseInt(b);
		}
		
		public float getFloatValueOfDict(String jsonFileString, int indexOfEndOfString){
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
				}else if(indexof == 0 && check == ' '){
					indexOfEndOfString++;
				}
			}
			char epic[] = new char[indexof];
			for(int x= 0; x < indexof;x++){
				epic[x] = potato[x];
			}
			String b = new String(epic);
			return Float.parseFloat(b);
		}
		
		public double getDoubleValueOfDict(String jsonFileString, int indexOfEndOfString){
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
				}else if(indexof == 0 && check == ' '){
					indexOfEndOfString++;
				}
			}
			char epic[] = new char[indexof];
			for(int x= 0; x < indexof;x++){
				epic[x] = potato[x];
			}
			String b = new String(epic);
			return Double.parseDouble(b);
		}
		
		public boolean getBoolValueOfDict(String jsonFileString, int indexOfEndOfString){
			int indexof = 0;
			boolean reading = true;
			char potato[] = new char[45];
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2 + indexof);
				if(check != ' ' && check != ':' && check != '{' && check != '}' && check != '[' && check != ']' && check != '"'){
					potato[indexof] = check;
					indexof++;
				}else if (indexof > 0){
					reading = false;
				}else if(indexof == 0 && check == ' '){
					indexOfEndOfString++;
				}
			}
			char epic[] = new char[indexof];
			for(int x= 0; x < indexof;x++){
				epic[x] = potato[x];
			}
			String b = new String(epic);
			if(b.equalsIgnoreCase("true") || b.equalsIgnoreCase("false")){
				return Boolean.parseBoolean(b);
			}else{
				System.out.println("Could Not Parse Bool");
				return false;
			}
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
			boolean reading = true;
			int Stringnum = 0;
			int index = 0;
			String[] bob = new String[25];
			char[][] chararray = new char[25][45];
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2 + index);
				if(check != '}' && check != '[' && check != ']'){
					System.out.println(check);
					if(check == ','){
						Stringnum++;
						indexOfEndOfString += (index+1);
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
			char epic[][] = new char[Stringnum+1][index+1];
			for(int y = 0; y < Stringnum; y++){
				for(int z= 0; z < index;z++){
					epic[y][z] = chararray[y][z];
				}
			}
			for(int x=0;x <= Stringnum; x++){
				
				String b = new String(epic[x]);
				bob[x] = b;
			}
			return bob;
		}
		
		public int[] getInt1DArray(String jsonFileString, int indexOfEndOfString){
			boolean reading = true;
			int Stringnum = 0;
			int index = 0;
			int[] bob = new int[50];
			char[][] chararray = new char[50][45];
			
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
				int parsedvalue = Integer.parseInt(parsedstring);
				bob[stringcur] = parsedvalue;
			}
			return bob;
		}
		
		
		public double[] getDouble1DArray(String jsonFileString, int indexOfEndOfString){
			boolean reading = true;
			int Stringnum = 0;
			int index = 0;
			double[] bob = new double[50];
			char[][] chararray = new char[50][45];
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2);
				if(check !='{' && check != '}' && check != '[' && check != ']'){
					if(check == ','){
						Stringnum++;
					}else{
						chararray[Stringnum][index] = check;
						index++;
					}
				}else if(check == '}'){
					reading = false;
				}				
			}
			for(int x=0;x <= Stringnum; x++){
				String b = new String(chararray[x]);
				double q = Double.parseDouble(b);
				bob[x] = q;
			}
			return bob;
		}
		
		public boolean[] getBool1DArray(String jsonFileString, int indexOfEndOfString){
			boolean reading = true;
			int Stringnum = 0;
			int index = 0;
			boolean[] bob = new boolean[100];
			char[][] chararray = new char[100][5];
			while(reading){
				char check = jsonFileString.charAt(indexOfEndOfString + 2);
				if(check !='{' && check != '}' && check != '[' && check != ']'){
					if(check == ','){
						Stringnum++;
					}else{
						chararray[Stringnum][index] = check;
						index++;
					}
				}else if(check == '}'){
					reading = false;
				}				
			}
			for(int x=0;x <= Stringnum; x++){
				String b = new String(chararray[x]);
				if(b.equalsIgnoreCase("true") || b.equalsIgnoreCase("false")){
					boolean q = Boolean.parseBoolean(b);		
					bob[x] = q;
				}else{
					System.out.println("Error:Cannot Parse Non-Boolean Value To Boolean");
				}
			}
			return bob;
		}
}
