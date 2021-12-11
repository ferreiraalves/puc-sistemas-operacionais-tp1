package utils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;

public class ConfigUtils {

    private static String mainCommand;
    private static HashMap <String, String> parameters;
    private static String inputFile = "input.txt";
    private static String outputFile = "output.txt";
    private static String log = "tela";
    private static int totems = 2;

    public static String getMainCommand() {
        return mainCommand;
    }

    public static void setMainCommand(String mainCommand) {
        ConfigUtils.mainCommand = mainCommand;
    }

    public static HashMap<String, String> getParameters() {
        return parameters;
    }

    public static void setParameters(HashMap<String, String> parameters) {
        ConfigUtils.parameters = parameters;
    }

    public static String getInputFile() {
        return inputFile;
    }

    public static void setInputFile(String inputFile) {
        ConfigUtils.inputFile = inputFile;
    }

    public static String getOutputFile() {
        return outputFile;
    }

    public static void setOutputFile(String outputFile) {
        ConfigUtils.outputFile = outputFile;
    }

    public static String getLog() {
        return log;
    }

    public static void setLog(String log) {
        ConfigUtils.log = log;
    }

    public static int getTotems() {
        return totems;
    }

    public static void setTotems(int totems) {
        ConfigUtils.totems = totems;
    }

    private static String validateMainCommand(String[] aux) {
        String potentialMainCommand = aux[0];
        if(potentialMainCommand.matches("simular|alterar|totalizar|finalizar")){
            return potentialMainCommand;
        }else{
            throw new InvalidParameterException("Invalid command. Please use simular, alterar, totalizar or finalizar");
        }
    }

    private static void validateParameter(int i, String[] parametersString) {      //checks corresponding value for parameter
        try {
            if (parametersString[i + 1].contains("-")){
                throw new InvalidParameterException();
            }else{
                parameters.put(parametersString[i].replace("-",""), parametersString[i + 1]);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidParameterException("Missing value for parameter " + parametersString[i]);
        }
    }

    private static void parseParameters(String[] aux) {
        parameters = new HashMap<String, String>();
        String [] parametersString = Arrays.copyOfRange(aux, 1, aux.length);
        for (int i = 0; i < parametersString.length; i++){
            if(parametersString[i].matches("-log|-pontos|-in|-out")){   //checks if current token is valid parameter
                validateParameter(i, parametersString);
            }
        }
    }

    private static void updateVaules() {                    //updates configuration based on parameters
        for(String parameterKey : parameters.keySet()){     //only passed parameters will be updated
            if(parameterKey.equals("log")){
                log = parameters.get(parameterKey);
            }else if(parameterKey.equals("pontos")){
                totems = Integer.parseInt(parameters.get(parameterKey));
            }else if(parameterKey.equals("in")){
                inputFile = parameters.get(parameterKey);
            }else if(parameterKey.equals("out")){
                outputFile = parameters.get(parameterKey);
            }
        }
    }

    public static void updateConfig(String command){
        String [] tokens = command.split(" ");
        mainCommand = validateMainCommand(tokens);      //checks if a valid command was passed
        parseParameters(tokens);                        //parses parameters
        updateVaules();                                 //updates config values

    }




}
