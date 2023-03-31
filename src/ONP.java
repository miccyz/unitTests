import java.util.function.Function;

public class ONP {
    private static TabStack<String> stack = new TabStack<String>();

    /**
     * Metoda oblicza wartość wyrażenia zapisanego w postaci ONP
     * @param rownanie równanie zapisane w postaci ONP
     * @return wartość obliczonego wyrażenia
     */
    public String obliczONP(String rownanie)
    {
        try
        {
            if (czyPoprawneRownanie(rownanie))
            {
                String wynik = "";
                Double a = 0.0;
                Double b = 0.0;
                for (int i = 0; i < rownanie.length(); i++)
                {
                    if (rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9' || rownanie.charAt(i) == '.')
                    {
                        wynik += rownanie.charAt(i);
                        if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9' || rownanie.charAt(i+1)=='.'))
                        {
                            stack.push(wynik);
                            wynik = "";
                        }
                    }
                    else if (rownanie.charAt(i) == '=' )
                        return stack.pop();
                    else if (rownanie.charAt(i) != ' ' || rownanie.charAt(i) == '.')
                    {
                        b = Double.parseDouble(stack.pop());
                        a = Double.parseDouble(stack.pop());
                        switch (rownanie.charAt(i))
                        {
                            case ('+'):
                            {
                                Dodawanie d = new Dodawanie();
                                d.dzialanie(a, b);
                                break;
                            }

                            case ('-'):
                            {
                                Odejmowanie o = new Odejmowanie();
                                o.dzialanie(a, b);
                                break;
                            }

                            case ('*'):
                            {
                                Mnozenie m = new Mnozenie();
                                m.dzialanie(a, b);
                                break;
                            }

                            case ('/'):
                            {
                                Dzielenie d = new Dzielenie();
                                d.dzialanie(a, b);
                                break;
                            }

                            case ('^'):
                            {
                                Potegowanie p = new Potegowanie();
                                p.dzialanie(a, b);
                                break;
                            }

                            case ('p'):
                            {
                                Pierwiastkowanie p = new Pierwiastkowanie();
                                p.dzialanie(b, a);
                                break;
                            }

                            case ('%'):
                            {
                                Modulo m = new Modulo();
                                m.dzialanie(a, b);
                                break;
                            }

                            case ('!'):
                            {
                                Silnia s = new Silnia();
                                s.dzialanie(a, b);
                                break;
                            }

                            default:
                            {
                                throw new IllegalArgumentException("Niepoprawny operator!" + rownanie.charAt(i));
                            }
                        }   // end switch
                    }   // end if
                }   // end for
            }   // end if
            return "0.0";
        }   // end try
        catch(IllegalArgumentException e)
        {
            System.out.println("Wyjatek: " + e.getMessage());
        }
        return "Bledne rownanie";
    }

    public String przeksztalcNaOnp(String rownanie)
    {
        try
        {
            if (czyPoprawneRownanie(rownanie))
            {
                String wynik = "";
                for (int i = 0; i < rownanie.length(); i++)
                {
                    if ((rownanie.charAt(i) >= '0' && rownanie.charAt(i) <= '9') || rownanie.charAt(i) == '.')
                    {
                        wynik += rownanie.charAt(i);
                        if (!(rownanie.charAt(i + 1) >= '0' && rownanie.charAt(i + 1) <= '9') && !(rownanie.charAt(i + 1) == '.'))
                            wynik += " ";
                    }
                    else
                    {
                        switch (rownanie.charAt(i))
                        {
                            case ('+'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("("))
                                    wynik = wynik + stack.pop() + " ";
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('-'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("("))
                                    wynik = wynik + stack.pop() + " ";
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('*'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                        && !stack.showValue(stack.getSize() - 1).equals("+")
                                        && !stack.showValue(stack.getSize() - 1).equals("-"))
                                    wynik = wynik + stack.pop() + " ";
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('/'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                        && !stack.showValue(stack.getSize() - 1).equals("+")
                                        && !stack.showValue(stack.getSize() - 1).equals("-"))
                                    wynik = wynik + stack.pop() + " ";
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('^'):
                            {
                                while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("^"))
                                    wynik = wynik + stack.pop() + " ";
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('p'):
                            {
                                while (stack.getSize() > 0 && stack.showValue(stack.getSize() - 1).equals("r"))
                                    wynik = wynik + rownanie.charAt(i);
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('%'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("(")
                                        && !stack.showValue(stack.getSize() - 1).equals("+")
                                        && !stack.showValue(stack.getSize() - 1).equals("-"))
                                    wynik = wynik + stack.pop() + " ";
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('!'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("("))
                                    wynik = wynik + rownanie.charAt(i);
                                String str = "1 " + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case ('('):
                            {
                                String str = "" + rownanie.charAt(i);
                                stack.push(str);
                                break;
                            }

                            case (')'):
                            {
                                while (stack.getSize() > 0 && !stack.showValue(stack.getSize() - 1).equals("("))
                                    wynik = wynik + stack.pop() + " ";
                                stack.pop();
                                break;
                            }

                            case ('='):
                            {
                                while (stack.getSize() > 0)
                                    wynik = wynik + stack.pop() + " ";
                                wynik += "=";
                                break;
                            }

                            default:
                            {
                                throw new IllegalArgumentException("Niepoprawny operator: " + rownanie.charAt(i));
                            }
                        }   // end switch
                    }   // end if
                }   // end for
                return wynik;
            }   // end if
        }   // end try
        catch(IllegalArgumentException e)
        {
            System.out.println("Wyjatek: " + e.getMessage());
        }
        return "null";
    }

    boolean czyPoprawneRownanie(String rownanie) throws IllegalArgumentException
    {
        if (rownanie.endsWith("="))
            return true;
        throw new IllegalArgumentException("Niepoprawne rownanie!");
    }

    abstract public class Dzialanie
    {
        public double wynik = 0.0;
        Function<Tuple<Double, Double>, Double> fun;
        Tuple<Double, Double> para;

        abstract public void dzialanie(double a, double b);
    }

    public class Dodawanie extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            para = new Tuple<>(a, b);
            fun = (p) -> p.getX() + p.getY();
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }

    public class Odejmowanie extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            para = new Tuple<>(a, b);
            fun = (p) -> p.getX() - p.getY();
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }

    public class Mnozenie extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            para = new Tuple<>(a, b);
            fun = (p) -> p.getX() * p.getY();
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);

        }
    }

    public class Dzielenie extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            if(b == 0)
                throw new IllegalArgumentException("Nie można dzielić przez zero!");
            para = new Tuple<>(a, b);
            fun = (p) -> p.getX() / p.getY();
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }

    public class Potegowanie extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            if(a == 0 && b == 0)
                throw new IllegalArgumentException("Przynajmniej jeden argument potegowania musi byc rozny od zera!");
            else if (a < 0 || b < 0)
                throw new IllegalArgumentException("Argumenty potegowania musza byc nieujemne!");
            para = new Tuple<>(a, b);
            fun = (p) -> Math.pow(p.getX(), p.getY());
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }

    public class Pierwiastkowanie extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            if(a == 0 && b == 0)
                throw new IllegalArgumentException("Przynajmniej jeden argument pierwiastkowania musi byc rozny od zera!");
            else if (a < 0 || b < 0)
                throw new IllegalArgumentException("Argumenty pierwiastkowania musza byc nieujemne!");
            para = new Tuple<>(a, b);
            fun = (p) -> Math.pow(p.getX(), 1.0 / p.getY());
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }

    public class Silnia extends Dzialanie
    {
        public double wynik = 1;
        @Override
        public void dzialanie(double a, double b)
        {
            if(a < 1)
                throw new IllegalArgumentException("Argument silni musi byc wiekszy od zera!");
            Function<Double, Double> fun = (x) -> wynik * x;
            for(double i = 1; i <= a; ++i)
                wynik = fun.apply(i);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }

    public class Modulo extends Dzialanie
    {
        @Override
        public void dzialanie(double a, double b)
        {
            if(b == 0)
                throw new IllegalArgumentException("Nie można dzielić przez zero!");
            Tuple<Integer, Integer>para = new Tuple<>((int)a, (int)b);
            Function<Tuple<Integer, Integer>, Integer> fun = (p) -> p.getX() % p.getY();
            wynik = fun.apply(para);

            String str = Double.toString(wynik);
            stack.push(str);
        }
    }
}
