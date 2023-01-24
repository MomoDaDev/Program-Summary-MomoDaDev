using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace _5EHIFTroestlComplexNumberCalculator
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public string Anzeige = String.Empty;
        private ComplexNumber ComplexNumberA = new ComplexNumber(0, 0);
        private ComplexNumber ComplexNumberB = new ComplexNumber(0, 0);
        public MainWindow()
        {
            InitializeComponent();

            // set Operator properties
            cbb_operator.SelectedIndex = 0;
            cbb_anzeige.SelectedIndex = 0;
        }

        private void UpdateResults(string _operator)
        {
            ComplexNumber result = null;
            try
            {
                switch (_operator)
                {
                    case "+":
                        result = ComplexNumberA + ComplexNumberB;
                        break;
                    case "*":
                        result = ComplexNumberA * ComplexNumberB;
                        break;
                    case "/":
                        result = ComplexNumberA / ComplexNumberB;
                        break;
                    default:
                        result = new ComplexNumber(0, 0);
                        break;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Exception: {ex.Message}");
                result = new ComplexNumber(0, 0);
            }
            txb_ergebnis_z1.Text = result.RealTeil.ToString();
            txb_ergebnis_z2.Text = result.ImaginaerTeil.ToString();
        }

        private void cbb_operator_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            lbl_operator.Content = (e.AddedItems[0] as ComboBoxItem).Content as string;
            UpdateResults((e.AddedItems[0] as ComboBoxItem).Content as string);
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if ((e.AddedItems[0] as ComboBoxItem).Content as string == "Rechteck")
            {
                lbl_operand_a1.Content = "Realteil A";
                lbl_operand_a2.Content = "Imaginärteil A";

                lbl_operand_b1.Content = "Realteil B";
                lbl_operand_b2.Content = "Imaginärteil B";

                lbl_ergebnis_z1.Content = "Realteil Z";
                lbl_ergebnis_z2.Content = "Imaginärteil Z";
            } else if ((e.AddedItems[0] as ComboBoxItem).Content as string == "Polar")
            {
                lbl_operand_a1.Content = "Betrag A";
                lbl_operand_a2.Content = "Winkel A";

                lbl_operand_b1.Content = "Betrag B";
                lbl_operand_b2.Content = "Winkel B";

                lbl_ergebnis_z1.Content = "Betrag Z";
                lbl_ergebnis_z2.Content = "Winkel Z";
            }

            Anzeige = (e.AddedItems[0] as ComboBoxItem).Content as string;
            UpdateResults(cbb_operator.Text);
        }

        private void NumberValidationTextBox(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex(@"[^0-9,-]+");
            e.Handled = regex.IsMatch(e.Text);
        }

        private void txb_operand_a1_TextChanged(object sender, TextChangedEventArgs e)
        {
            double.TryParse(txb_operand_a1.Text, out double x);
            ComplexNumberA.RealTeil = x;
            UpdateResults(cbb_operator.Text);
        }

        private void txb_operand_a2_TextChanged(object sender, TextChangedEventArgs e)
        {
            double.TryParse(txb_operand_a2.Text, out double x);
            ComplexNumberA.ImaginaerTeil = x;
            UpdateResults(cbb_operator.Text);
        }

        private void txb_operand_b1_TextChanged(object sender, TextChangedEventArgs e)
        {
            double.TryParse(txb_operand_b1.Text, out double x);
            ComplexNumberB.RealTeil = x;
            UpdateResults(cbb_operator.Text);
        }

        private void txb_operand_b2_TextChanged(object sender, TextChangedEventArgs e)
        {
            double.TryParse(txb_operand_b2.Text, out double x);
            ComplexNumberB.ImaginaerTeil = x;
            UpdateResults(cbb_operator.Text);
        }
    }
}
