using System;
using System.Windows;

namespace _5EHIFTroestlComplexNumberCalculator
{ 
    public class ComplexNumber
    {
        public double RealTeil { get; set; }
        public double ImaginaerTeil { get; set; }

        public ComplexNumber(double realTeil, double imaginaerTeil)
        {
            RealTeil = realTeil;
            ImaginaerTeil = imaginaerTeil;
        }

        private static string GetAnzeige() => (Window.GetWindow(App.Current.MainWindow) as MainWindow).Anzeige;


        public static ComplexNumber operator +(ComplexNumber a, ComplexNumber b)
        {
            try
            {
                string anzeige = GetAnzeige();
                if (anzeige == "Rechteck")
                {
                    return new ComplexNumber(a.RealTeil + b.RealTeil, a.ImaginaerTeil + b.ImaginaerTeil);
                }
                else if (anzeige == "Polar")
                {
                    double a1 = a.RealTeil * Math.Cos(a.ImaginaerTeil);
                    double a2 = a.RealTeil * Math.Sin(a.ImaginaerTeil);

                    double b1 = b.RealTeil * Math.Cos(b.ImaginaerTeil);
                    double b2 = b.RealTeil * Math.Sin(b.ImaginaerTeil);

                    double z1 = a1 + b1;
                    double z2 = a2 + b2;

                    double rawAngle = 360 % Math.Atan(z2 / z1);

                    return new ComplexNumber(Math.Sqrt(Math.Pow(z1, 2) + Math.Pow(z2, 2)), rawAngle < 0 ? 360 - rawAngle : rawAngle);
                }
            }
            catch (Exception ex)
            {
                System.Console.WriteLine($"Exception: {ex.Message}");
            }
            return new ComplexNumber(0, 0);
        }

        public static ComplexNumber operator /(ComplexNumber a, ComplexNumber b)
        {
            try
            {
                string anzeige = GetAnzeige();
                if (anzeige == "Rechteck")
                {
                    return new ComplexNumber(a.RealTeil / b.RealTeil, a.ImaginaerTeil / b.ImaginaerTeil);
                }
                else if (anzeige == "Polar")
                {
                    return new ComplexNumber(a.RealTeil / b.RealTeil, Math.Abs(360 % a.ImaginaerTeil - b.ImaginaerTeil));
                }
            }
            catch (Exception ex)
            {
                System.Console.WriteLine($"Exception: {ex.Message}");
            }
            return new ComplexNumber(0, 0);
        }

        public static ComplexNumber operator *(ComplexNumber a, ComplexNumber b)
        {
            try
            {
                string anzeige = GetAnzeige();
                if (anzeige == "Rechteck")
                {
                    return new ComplexNumber(a.RealTeil * b.RealTeil, a.ImaginaerTeil * b.ImaginaerTeil);
                }
                else if (anzeige == "Polar")
                {
                    return new ComplexNumber(a.RealTeil * b.RealTeil, Math.Abs(360 % a.ImaginaerTeil + b.ImaginaerTeil));
                }
            }
            catch (Exception ex)
            {
                System.Console.WriteLine($"Exception: {ex.Message}");
            }
            return new ComplexNumber(0, 0);
        }
    }

    
}