import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pegando dados básicos
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu peso (kg): ");
        double peso = scanner.nextDouble();
        System.out.print("Digite sua altura (cm): ");
        int altura = scanner.nextInt();
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();

        scanner.nextLine(); // Limpa buffer
        System.out.print("Digite seu sexo (M/F): ");
        String sexo = scanner.nextLine().toUpperCase();

        while (!sexo.equals("F") && !sexo.equals("M")) {
            System.out.print("Sexo inválido! Digite novamente (M/F): ");
            sexo = scanner.nextLine().toUpperCase();
        }

        // Definição do fator atividade
        double fatorAtividade;
        while (true) {
            System.out.println("Você se considera: " +
                    "\n1 - Pouco Ativo " +
                    "\n2 - Moderadamente Ativo " +
                    "\n3 - Muito Ativo");
            int atividade = scanner.nextInt();

            switch (atividade) {
                case 1:
                    fatorAtividade = 1.3;
                    break;
                case 2:
                    fatorAtividade = 1.5;
                    break;
                case 3:
                    fatorAtividade = 1.7;
                    break;
                default:
                    System.out.println("Opção inválida! Digite novamente.");
                    continue;
            }
            break;
        }


        // Cálculo da Taxa Metabólica Basal (TMB)
        double taxaMetabolicaBasal = (sexo.equals("M")) ?
                (13.75 * peso) + (5 * altura) - (6.75 * idade) + 66.5 :
                (9.56 * peso) + (1.85 * altura) - (4.68 * idade) + 65.71;

        // Cálculo do Gasto Energético Total (TMT)
        double taxaMetabolicaTotal = taxaMetabolicaBasal * fatorAtividade;

        // Déficit ou superávit
        System.out.print("Digite o valor da diferença de calorias que você quer ter (negativo para superávit): ");
        int defictKcal = scanner.nextInt();
        int kcalTotais = (int) (taxaMetabolicaTotal - defictKcal);

        // Cálculo de macronutrientes
        double gramaProteinas = 2 * peso;
        double gramaLipideos = 1 * peso;
        double kcalProteinas = gramaProteinas * 4;
        double kcalLipideos = gramaLipideos * 9;
        double kcalCarboidratos = kcalTotais - (kcalProteinas + kcalLipideos);
        double gramaCarboidratos = kcalCarboidratos / 4;

        // Exibição dos resultados
        System.out.println("\n---- Ficha Técnica ----\n");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Altura: " + altura + " cm");
        System.out.println("Peso atual: " + peso + " kg\n");

        System.out.printf("Taxa Metabólica Basal (TMB): %.0f kcal\n", taxaMetabolicaBasal);
        System.out.printf("Gasto Total de Calorias (TMT): %.0f kcal\n\n", taxaMetabolicaTotal);

        System.out.println("Com o déficit de " + defictKcal + " kcal, você precisará comer:");
        System.out.println(kcalTotais + " kcal no total, sendo dessas:");
        System.out.printf("%.0f kcal de proteínas (%.0f g)\n", kcalProteinas, gramaProteinas);
        System.out.printf("%.0f kcal de lipídeos (%.0f g)\n", kcalLipideos, gramaLipideos);
        System.out.printf("%.0f kcal de carboidratos (%.0f g)\n", kcalCarboidratos, gramaCarboidratos);

        scanner.close();
    }
}
