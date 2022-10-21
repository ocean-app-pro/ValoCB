
import input.DataProviderImpl;
import input.DataReader;
import input.FromCsvReader;
import org.apache.commons.cli.*;
import output.DataWriter;
import output.ToCsvWriter;
import processing.CalculatorService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        CommandLine cmd = getCommandLine(args);

        String reportClientOutput = Optional.ofNullable(cmd.getOptionValue("outputClient")).orElse(System.getProperty("user.home"));
        String reportPtfOutput = Optional.ofNullable(cmd.getOptionValue("outputPtf")).orElse(System.getProperty("user.home"));

        String forexPath = "Forex.csv";
        String ptfPath = "Prices.csv";
        String clientPath = "Product.csv";

        DataReader dataReader = new FromCsvReader();
        DataWriter dataWriter = new ToCsvWriter();

        DataProviderImpl dataProvider = new DataProviderImpl(dataReader, forexPath, ptfPath, clientPath);
        CalculatorService calculatorService = new CalculatorService(dataProvider, dataWriter);


        Map<String, BigDecimal> sumByPtf = calculatorService.calculateSumByPortfolio();
        String[] headerPtf = { "PTF", "Price" };
        dataWriter.write(sumByPtf, reportPtfOutput+"/Reporting-portfolio.csv", headerPtf);

        Map<String, BigDecimal> sumByClient = calculatorService.calculateSumByClient();
        String[] headerClient = { "Client", "Capital" };
        dataWriter.write(sumByClient, reportClientOutput +"/Reporting-client.csv", headerClient);



    }

    private static CommandLine getCommandLine(String[] args) {
        Options options = new Options();

        Option input = new Option("c", "outputClient", true, "Path where to write clients report");
        input.setRequired(false);
        options.addOption(input);

        Option output = new Option("p", "outputPtf", true, "Path where to write portfolios report");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        return cmd;
    }

}
