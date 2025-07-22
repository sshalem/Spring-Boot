import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Log {

    /**
     * ANSI can refer to the "American National Standards Institute" ANSI Colors
     * code
     * https://stackoverflow.com/questions/67241111/python-colored-text-to-the-terminal
     * <p>
     * https://www.w3schools.blog/ansi-colors-java
     */
//	public final static String ESC_START = "\033[";
//	public final static String ESC_END = "m";
//	public final static String REGULAR = "0;";
//	public final static String BOLD = "1;";
//	public final static String UNDERLINE = "4;";
    // public final static String BLACK_FG = "30";
//	public final static String RED_FG = "31";
//	public final static String GREEN_FG = "32";
//	public final static String YELLOW_FG = "33";
//	public final static String BLUE_FG = "34";
//	public final static String MAGENTA_FG = "35";
//	public final static String CYAN_FG = "36";
//	public final static String WHITE_FG = "37";
//	public final static String DEFAULT_FG = "39";
    // Reset
    private static final String RESET = "\033[0m"; // Text Reset
    /**
     * Code with pattern of \033[X;XXm
     */
    // Regular Colors
    private static final String BLACK = "\033[0;30m"; // BLACK
    private static final String RED = "\033[0;31m"; // RED
    private static final String GREEN = "\033[0;32m"; // GREEN
    private static final String YELLOW = "\033[0;33m"; // YELLOW
    private static final String BLUE = "\033[0;34m"; // BLUE
    private static final String PURPLE = "\033[0;35m"; // PURPLE
    private static final String CYAN = "\033[0;36m"; // CYAN
    private static final String WHITE = "\033[0;37m"; // WHITE
    // Bold
    private static final String BLACK_BOLD = "\033[1;30m"; // BLACK
    private static final String RED_BOLD = "\033[1;31m"; // RED
    private static final String GREEN_BOLD = "\033[1;32m"; // GREEN
    private static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    private static final String BLUE_BOLD = "\033[1;34m"; // BLUE
    private static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    private static final String CYAN_BOLD = "\033[1;36m"; // CYAN
    private static final String WHITE_BOLD = "\033[1;37m"; // WHITE
    // Underline
    private static final String BLACK_UNDERLINED = "\033[4;30m"; // BLACK
    private static final String RED_UNDERLINED = "\033[4;31m"; // RED
    private static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN
    private static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    private static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE
    private static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    private static final String CYAN_UNDERLINED = "\033[4;36m"; // CYAN
    @SuppressWarnings("unused")
    private static final String WHITE_UNDERLINED = "\033[4;37m"; // WHITE
    // High Intensity
    private static final String BLACK_BRIGHT = "\033[0;90m"; // BLACK
    private static final String RED_BRIGHT = "\033[0;91m"; // RED
    private static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
    private static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    private static final String BLUE_BRIGHT = "\033[0;94m"; // BLUE
    private static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    private static final String CYAN_BRIGHT = "\033[0;96m"; // CYAN
    private static final String WHITE_BRIGHT = "\033[0;97m"; // WHITE
    // Bold High Intensity
    private static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    private static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
    private static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    private static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    private static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE
    private static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    private static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
    private static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    /**
     * Background Colors
     */
    // Background
    private static final String BLACK_BACKGROUND = "\033[40m"; // BLACK
    private static final String RED_BACKGROUND = "\033[41m"; // RED
    private static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
    private static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    private static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
    private static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    private static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
    private static final String WHITE_BACKGROUND = "\033[47m"; // WHITE
    // High Intensity backgrounds
    private static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    private static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    private static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    private static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    private static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    private static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    private static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
    private static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE

    
    
    /******************
     * Regular colors
     ******************/
    public static void black(Object message) {
        System.out.println(BLACK + message + RESET);
    }

    public static void red(Object message) {
        System.out.println(RED + message + RESET);
    }

    public static void green(Object message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void yellow(Object message) {
        System.out.println(YELLOW + message + RESET);
    }

    public static void blue(Object message) {
        System.out.println(BLUE + message + RESET);
    }

    public static void purple(Object message) {
        System.out.println(PURPLE + message + RESET);
    }

    public static void cyan(Object message) {
        System.out.println(CYAN + message + RESET);
    }

    public static void white(Object message) {
        System.out.println(WHITE + message + RESET);
    }

    /******************
     * Bold colors
     ******************/
    public static void blackBold(Object message) {
        System.out.println(BLACK_BOLD + message + RESET);
    }

    public static void redBold(Object message) {
        System.out.println(RED_BOLD + message + RESET);
    }

    public static void greenBold(Object message) {
        System.out.println(GREEN_BOLD + message + RESET);
    }

    public static void yellowBold(Object message) {
        System.out.println(YELLOW_BOLD + message + RESET);
    }

    public static void blueBold(Object message) {
        System.out.println(BLUE_BOLD + message + RESET);
    }

    public static void purpleBold(Object message) {
        System.out.println(PURPLE_BOLD + message + RESET);
    }

    public static void cyanBold(Object message) {
        System.out.println(CYAN_BOLD + message + RESET);
    }

    public static void whiteBold(Object message) {
        System.out.println(WHITE_BOLD + message + RESET);
    }

    /******************
     * Underline colors
     ******************/
    public static void blackUnderlined(Object message) {
        System.out.println(BLACK_UNDERLINED + message + RESET);
    }

    public static void redUnderlined(Object message) {
        System.out.println(RED_UNDERLINED + message + RESET);
    }

    public static void greenUnderlined(Object message) {
        System.out.println(GREEN_UNDERLINED + message + RESET);
    }

    public static void yellowUnderlined(Object message) {
        System.out.println(YELLOW_UNDERLINED + message + RESET);
    }

    public static void blueUnderlined(Object message) {
        System.out.println(BLUE_UNDERLINED + message + RESET);
    }

    public static void purpleUnderlined(Object message) {
        System.out.println(PURPLE_UNDERLINED + message + RESET);
    }

    public static void cyanUnderlined(Object message) {
        System.out.println(CYAN_UNDERLINED + message + RESET);
    }

    public static void whiteUnderlined(Object message) {
        System.out.println(WHITE + message + RESET);
    }

    /************************************
     * High Intensity (Bright) colors
     ************************************/
    public static void blackBright(Object message) {
        System.out.println(BLACK_BRIGHT + message + RESET);
    }

    public static void redBright(Object message) {
        System.out.println(RED_BRIGHT + message + RESET);
    }

    public static void greenBright(Object message) {
        System.out.println(GREEN_BRIGHT + message + RESET);
    }

    public static void yellowBright(Object message) {
        System.out.println(YELLOW_BRIGHT + message + RESET);
    }

    public static void blueBright(Object message) {
        System.out.println(BLUE_BRIGHT + message + RESET);
    }

    public static void purpleBright(Object message) {
        System.out.println(PURPLE_BRIGHT + message + RESET);
    }

    public static void cyanBright(Object message) {
        System.out.println(CYAN_BRIGHT + message + RESET);
    }

    public static void whiteBright(Object message) {
        System.out.println(WHITE_BRIGHT + message + RESET);
    }

    /************************************
     * Bold High Intensity (Bright) colors
     ************************************/
    public static void blackBoldBright(Object message) {
        System.out.println(BLACK_BOLD_BRIGHT + message + RESET);
    }

    public static void redBoldBright(Object message) {
        System.out.println(RED_BOLD_BRIGHT + message + RESET);
    }

    public static void greenBoldBright(Object message) {
        System.out.println(GREEN_BOLD_BRIGHT + message + RESET);
    }

    public static void yellowBoldBright(Object message) {
        System.out.println(YELLOW_BOLD_BRIGHT + message + RESET);
    }

    public static void blueBoldBright(Object message) {
        System.out.println(BLUE_BOLD_BRIGHT + message + RESET);
    }

    public static void purpleBoldBright(Object message) {
        System.out.println(PURPLE_BOLD_BRIGHT + message + RESET);
    }

    public static void cyanBoldBright(Object message) {
        System.out.println(CYAN_BOLD_BRIGHT + message + RESET);
    }

    public static void whiteBoldBright(Object message) {
        System.out.println(WHITE_BOLD_BRIGHT + message + RESET);
    }

    /******************
     * Background colors
     ******************/
    public static void blackBackground(Object message) {
        System.out.println(BLACK_BACKGROUND + message + RESET);
    }

    public static void redBackground(Object message) {
        System.out.println(RED_BACKGROUND + message + RESET);
    }

    public static void greenBackground(Object message) {
        System.out.println(GREEN_BACKGROUND + message + RESET);
    }

    public static void yellowBackground(Object message) {
        System.out.println(YELLOW_BACKGROUND + message + RESET);
    }

    public static void blueBackground(Object message) {
        System.out.println(BLUE_BACKGROUND + message + RESET);
    }

    public static void purpleBackground(Object message) {
        System.out.println(PURPLE_BACKGROUND + message + RESET);
    }

    public static void cyanBackground(Object message) {
        System.out.println(CYAN_BACKGROUND + message + RESET);
    }

    public static void whiteBackground(Object message) {
        System.out.println(WHITE_BACKGROUND + message + RESET);
    }

    /******************************
     * Background Bright colors
     ******************************/
    public static void blackBackgroundBright(Object message) {
        System.out.println(BLACK_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void redBackgroundBright(Object message) {
        System.out.println(RED_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void greenBackgroundBright(Object message) {
        System.out.println(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void yellowBackgroundBright(Object message) {
        System.out.println(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void blueBackgroundBright(Object message) {
        System.out.println(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void purpleBackgroundBright(Object message) {
        System.out.println(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void cyanBackgroundBright(Object message) {
        System.out.println(CYAN_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void whiteBackgroundBright(Object message) {
        System.out.println(WHITE_BACKGROUND_BRIGHT + message + RESET);
    }

    
    
    
    
    
    /******************************
     * SLF4J Info Logger colors
     ******************************/
    public static void infoRed(Logger LOGGER, Object message) {
        LOGGER.info(RED + message + RESET);
    }

    public static void infoRedBackground(Logger LOGGER, Object message) {
        LOGGER.info(RED_BACKGROUND + message + RESET);
    }

    public static void infoRedBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.info(RED_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoGreen(Logger LOGGER, Object message) {
        LOGGER.info(GREEN + message + RESET);
    }

    public static void infoGreenBackground(Logger LOGGER, Object message) {
        LOGGER.info(GREEN_BACKGROUND + message + RESET);
    }

    public static void infoGreenBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.info(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoYellow(Logger LOGGER, Object message) {
        LOGGER.info(YELLOW + message + RESET);
    }

    public static void infoYellowBackground(Logger LOGGER, Object message) {
        LOGGER.info(YELLOW_BACKGROUND + message + RESET);
    }

    public static void infoYellowBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.info(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoBlue(Logger LOGGER, Object message) {
        LOGGER.info(BLUE + message + RESET);
    }

    public static void infoBlueBackground(Logger LOGGER, Object message) {
        LOGGER.info(BLUE_BACKGROUND + message + RESET);
    }

    public static void infoBlueBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.info(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoPurple(Logger LOGGER, Object message) {
        LOGGER.info(PURPLE + message + RESET);
    }

    public static void infoPurpleBackground(Logger LOGGER, Object message) {
        LOGGER.info(PURPLE_BACKGROUND + message + RESET);
    }

    public static void infoPurpleBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.info(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }
    
    
    
    
    /******************************
     * SLF4J Warning Logger colors
     ******************************/
    public static void warnRed(Logger LOGGER, Object message) {
        LOGGER.warn(RED + message + RESET);
    }

    public static void warnRedBackground(Logger LOGGER, Object message) {
        LOGGER.warn(RED_BACKGROUND + message + RESET);
    }

    public static void warnRedBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.warn(RED_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnGreen(Logger LOGGER, Object message) {
        LOGGER.warn(GREEN + message + RESET);
    }

    public static void warnGreenBackground(Logger LOGGER, Object message) {
        LOGGER.warn(GREEN_BACKGROUND + message + RESET);
    }

    public static void warnGreenBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.warn(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnYellow(Logger LOGGER, Object message) {
        LOGGER.warn(YELLOW + message + RESET);
    }

    public static void warnYellowBackground(Logger LOGGER, Object message) {
        LOGGER.warn(YELLOW_BACKGROUND + message + RESET);
    }

    public static void warnYellowBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.warn(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnBlue(Logger LOGGER, Object message) {
        LOGGER.warn(BLUE + message + RESET);
    }

    public static void warnBlueBackground(Logger LOGGER, Object message) {
        LOGGER.warn(BLUE_BACKGROUND + message + RESET);
    }

    public static void warnBlueBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.warn(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnPurple(Logger LOGGER, Object message) {
        LOGGER.warn(PURPLE + message + RESET);
    }

    public static void warnPurpleBackground(Logger LOGGER, Object message) {
        LOGGER.warn(PURPLE_BACKGROUND + message + RESET);
    }

    public static void warnPurpleBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.warn(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }

    /******************************
     * SLF4J Error Logger colors
     ******************************/
    public static void errorRed(Logger LOGGER, Object message) {
        LOGGER.error(RED + message + RESET);
    }

    public static void errorRedBackground(Logger LOGGER, Object message) {
        LOGGER.error(RED_BACKGROUND + message + RESET);
    }

    public static void errorRedBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.error(RED_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorGreen(Logger LOGGER, Object message) {
        LOGGER.error(GREEN + message + RESET);
    }

    public static void errorGreenBackground(Logger LOGGER, Object message) {
        LOGGER.error(GREEN_BACKGROUND + message + RESET);
    }

    public static void errorGreenBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.error(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorYellow(Logger LOGGER, Object message) {
        LOGGER.error(YELLOW + message + RESET);
    }

    public static void errorYellowBackground(Logger LOGGER, Object message) {
        LOGGER.error(YELLOW_BACKGROUND + message + RESET);
    }

    public static void errorYellowBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.error(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorBlue(Logger LOGGER, Object message) {
        LOGGER.error(BLUE + message + RESET);
    }

    public static void errorBlueBackground(Logger LOGGER, Object message) {
        LOGGER.error(BLUE_BACKGROUND + message + RESET);
    }

    public static void errorBlueBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.error(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorPurple(Logger LOGGER, Object message) {
        LOGGER.error(PURPLE + message + RESET);
    }

    public static void errorPurpleBackground(Logger LOGGER, Object message) {
        LOGGER.error(PURPLE_BACKGROUND + message + RESET);
    }

    public static void errorPurpleBackgroundBright(Logger LOGGER, Object message) {
        LOGGER.error(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }
    
    
    
    
    /******************
     * Regular colors 
     * But converted to JSON format 
     * And displayed as JSON in console 
     * 
     * (1) create ObjectMapper.
     * To convert POJO to JSON format and show it in console as JSON format 
     * ObjectMapper mapper = new ObjectMapper();
     * 
     * (2) This Convert POJO to  JSON
     * -  Pretty JSON format
     * -  write value as String
     *  String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
     ******************/
    public static void blackJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK + message + RESET);
    }

    public static void redJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED + message + RESET);
    }

    public static void greenJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN + message + RESET);
    }

    public static void yellowJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW + message + RESET);
    }

    public static void blueJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE + message + RESET);
    }

    public static void purpleJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE + message + RESET);
    }

    public static void cyanJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN + message + RESET);
    }

    public static void whiteJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE + message + RESET);
    }

    /******************
     * Bold colors
     ******************/
    public static void blackBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK_BOLD + message + RESET);
    }

    public static void redBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED_BOLD + message + RESET);
    }

    public static void greenBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN_BOLD + message + RESET);
    }

    public static void yellowBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW_BOLD + message + RESET);
    }

    public static void blueBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE_BOLD + message + RESET);
    }

    public static void purpleBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE_BOLD + message + RESET);
    }

    public static void cyanBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN_BOLD + message + RESET);
    }

    public static void whiteBoldJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE_BOLD + message + RESET);
    }

    /******************
     * Underline colors
     ******************/
    public static void blackUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK_UNDERLINED + message + RESET);
    }

    public static void redUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED_UNDERLINED + message + RESET);
    }

    public static void greenUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN_UNDERLINED + message + RESET);
    }

    public static void yellowUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW_UNDERLINED + message + RESET);
    }

    public static void blueUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE_UNDERLINED + message + RESET);
    }

    public static void purpleUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE_UNDERLINED + message + RESET);
    }

    public static void cyanUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN_UNDERLINED + message + RESET);
    }

    public static void whiteUnderlinedJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE + message + RESET);
    }

    /************************************
     * High Intensity (Bright) colors
     ************************************/
    public static void blackBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK_BRIGHT + message + RESET);
    }

    public static void redBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED_BRIGHT + message + RESET);
    }

    public static void greenBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN_BRIGHT + message + RESET);
    }

    public static void yellowBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW_BRIGHT + message + RESET);
    }

    public static void blueBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE_BRIGHT + message + RESET);
    }

    public static void purpleBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE_BRIGHT + message + RESET);
    }

    public static void cyanBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN_BRIGHT + message + RESET);
    }

    public static void whiteBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE_BRIGHT + message + RESET);
    }

    /************************************
     * Bold High Intensity (Bright) colors
     ************************************/
    public static void blackBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK_BOLD_BRIGHT + message + RESET);
    }

    public static void redBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED_BOLD_BRIGHT + message + RESET);
    }

    public static void greenBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN_BOLD_BRIGHT + message + RESET);
    }

    public static void yellowBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW_BOLD_BRIGHT + message + RESET);
    }

    public static void blueBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE_BOLD_BRIGHT + message + RESET);
    }

    public static void purpleBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE_BOLD_BRIGHT + message + RESET);
    }

    public static void cyanBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN_BOLD_BRIGHT + message + RESET);
    }

    public static void whiteBoldBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE_BOLD_BRIGHT + message + RESET);
    }

    /******************
     * Background colors
     ******************/
    public static void blackBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK_BACKGROUND + message + RESET);
    }

    public static void redBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED_BACKGROUND + message + RESET);
    }

    public static void greenBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN_BACKGROUND + message + RESET);
    }

    public static void yellowBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW_BACKGROUND + message + RESET);
    }

    public static void blueBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE_BACKGROUND + message + RESET);
    }

    public static void purpleBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE_BACKGROUND + message + RESET);
    }

    public static void cyanBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN_BACKGROUND + message + RESET);
    }

    public static void whiteBackgroundJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE_BACKGROUND + message + RESET);
    }

    /******************************
     * Background Bright colors
     ******************************/
    public static void blackBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLACK_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void redBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(RED_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void greenBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void yellowBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void blueBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void purpleBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void cyanBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(CYAN_BACKGROUND_BRIGHT + message + RESET);
    }

    public static void whiteBackgroundBrightJson(Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        System.out.println(WHITE_BACKGROUND_BRIGHT + message + RESET);
    }

    
    /**
     * In the following methods
     * I show in console the data with the 
     * 'LOGGER' received from the Class itself
     */
    
    
    
    /******************************
     * SLF4J Info Logger colors
     ******************************/
    public static void infoRedJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(RED + message + RESET);
    }

    public static void infoRedBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(RED_BACKGROUND + message + RESET);
    }

    public static void infoRedBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(RED_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoGreenJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(GREEN + message + RESET);
    }

    public static void infoGreenBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(GREEN_BACKGROUND + message + RESET);
    }

    public static void infoGreenBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoYellowJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(YELLOW + message + RESET);
    }

    public static void infoYellowBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(YELLOW_BACKGROUND + message + RESET);
    }

    public static void infoYellowBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoBlueJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(BLUE + message + RESET);
    }
    
    public static void infoBlueBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(BLUE_BRIGHT + message + RESET);
    }
    
    public static void infoBlueBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(BLUE_BACKGROUND + message + RESET);
    }

    public static void infoBlueBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }

    // -------------------------------------------------------------
    
    public static void infoCyanJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);    	
        LOGGER.info(CYAN + message + RESET);
    }
    
    public static void infoCyanBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(CYAN_BACKGROUND + message + RESET);
    }

    public static void infoCyanBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(CYAN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void infoPurpleJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(PURPLE + message + RESET);
    }

    public static void infoPurpleBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(PURPLE_BACKGROUND + message + RESET);
    }

    public static void infoPurpleBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.info(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }
    
    
    
    
    /******************************
     * SLF4J Warning Logger colors
     ******************************/
    public static void warnRedJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(RED + message + RESET);
    }

    public static void warnRedBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(RED_BACKGROUND + message + RESET);
    }

    public static void warnRedBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(RED_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnGreenJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(GREEN + message + RESET);
    }

    public static void warnGreenBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(GREEN_BACKGROUND + message + RESET);
    }

    public static void warnGreenBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnYellowJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(YELLOW + message + RESET);
    }

    public static void warnYellowBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(YELLOW_BACKGROUND + message + RESET);
    }

    public static void warnYellowBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnBlueJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(BLUE + message + RESET);
    }

    public static void warnBlueBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(BLUE_BACKGROUND + message + RESET);
    }

    public static void warnBlueBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void warnPurpleJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(PURPLE + message + RESET);
    }

    public static void warnPurpleBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(PURPLE_BACKGROUND + message + RESET);
    }

    public static void warnPurpleBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.warn(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }

    /******************************
     * SLF4J Error Logger colors
     ******************************/
    public static void errorRedJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(RED + message + RESET);
    }

    public static void errorRedBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(RED_BACKGROUND + message + RESET);
    }

    public static void errorRedBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(RED_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorGreenJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(GREEN + message + RESET);
    }

    public static void errorGreenBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(GREEN_BACKGROUND + message + RESET);
    }

    public static void errorGreenBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(GREEN_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorYellowJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(YELLOW + message + RESET);
    }

    public static void errorYellowBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(YELLOW_BACKGROUND + message + RESET);
    }

    public static void errorYellowBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(YELLOW_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorBlueJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(BLUE + message + RESET);
    }

    public static void errorBlueBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(BLUE_BACKGROUND + message + RESET);
    }

    public static void errorBlueBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(BLUE_BACKGROUND_BRIGHT + message + RESET);
    }
    // -------------------------------------------------------------

    public static void errorPurpleJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(PURPLE + message + RESET);
    }

    public static void errorPurpleBackgroundJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(PURPLE_BACKGROUND + message + RESET);
    }

    public static void errorPurpleBackgroundBrightJson(Logger LOGGER, Object msg) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
     	String message = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);
        LOGGER.error(PURPLE_BACKGROUND_BRIGHT + message + RESET);
    }
}