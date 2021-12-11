package utils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;

public class ConfigUtils {

    public static String mainCommand;
    public static HashMap <String, String> parameters;
    public static String inputFile = "input.txt";
    public static String outputFile = "output.txt";
    public static String log = "tela";
    public static int tokens = 2;

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

    public static int getTokens() {
        return tokens;
    }

    public static void setTokens(int tokens) {
        ConfigUtils.tokens = tokens;
    }

    private static String validateMainCommand(String[] aux) {
        String potentialMainCommand = aux[0];
        if(potentialMainCommand.matches("simular|alterar|totalizar|finalizar")){
            return potentialMainCommand;
        }else{
            throw new InvalidParameterException("Invalid command. Please use simular, alterar, totalizar or finalizar");
        }
    }

    private static void validateParameter(int i, String[] parametersString) {
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
            if(parametersString[i].matches("-log|-pontos|-in|-out")){
                validateParameter(i, parametersString);
            }
        }

    }

    private static void updateVaules() {
        for(String parameterKey : parameters.keySet()){
            if(parameterKey.equals("log")){
                log = parameters.get(parameterKey);
            }else if(parameterKey.equals("pontos")){
                tokens = Integer.parseInt(parameters.get(parameterKey));
            }else if(parameterKey.equals("in")){
                inputFile = parameters.get(parameterKey);
            }else if(parameterKey.equals("out")){
                outputFile = parameters.get(parameterKey);
            }
        }
    }

    public static void updateConfig(String command){
        String [] tokens = command.split(" ");
        mainCommand = validateMainCommand(tokens);
        parseParameters(tokens);
        updateVaules();

    }




}
