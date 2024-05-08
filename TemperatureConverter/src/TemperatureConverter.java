import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputScale, outputScale;

        do {
            System.out.println("Escolha a escala de entrada: ");
            System.out.println("1. Celsius");
            System.out.println("2. Fahrenheit");
            System.out.println("3. Kelvin");
            inputScale = scanner.nextInt();
        } while (inputScale < 1 || inputScale > 3);

        System.out.print("Digite a temperatura: ");
        double temperature = scanner.nextDouble();

        do {
            System.out.println("Escolha a escala de saída: ");
            System.out.println("1. Celsius");
            System.out.println("2. Fahrenheit");
            System.out.println("3. Kelvin");
            outputScale = scanner.nextInt();
        } while (outputScale < 1 || outputScale > 3);

        double result = temperatureConversion(temperature, inputScale, outputScale);

        System.out.println("Resultado: " + String.format("%.2f", result));

        scanner.close();
    }

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double celsiusToKevin(double celsius) {
        return celsius + 273.15;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    public static double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5 / 9;
    }

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 9 / 5) - 459.67;
    }

    public static double temperatureConversion(double temperature, int inputScale, int outputScale) {
        double result = 0;

        switch (inputScale) {
            case 1:
                if (outputScale == 1)
                    return temperature;
                else if (outputScale == 2)
                    return celsiusToFahrenheit(temperature);
                else if (outputScale == 3)
                    return celsiusToKevin(temperature);
                break;
            case 2:
                if (outputScale == 1)
                    return fahrenheitToCelsius(temperature);
                else if (outputScale == 2)
                    return temperature;
                else if (outputScale == 3)
                    return fahrenheitToKelvin(temperature);
                break;
            case 3:
                if (outputScale == 1)
                    return kelvinToCelsius(temperature);
                else if (outputScale == 2)
                    return kelvinToFahrenheit(temperature);
                else if (outputScale == 3)
                    return temperature;
                break;
        }
        return result;
    }
}
