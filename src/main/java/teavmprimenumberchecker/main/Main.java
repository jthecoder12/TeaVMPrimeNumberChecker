package teavmprimenumberchecker.main;

import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLInputElement;

import java.math.BigInteger;

public final class Main {
    public static void main(String[] args) {
        HTMLDocument document = HTMLDocument.current();

        HTMLInputElement numberInput = (HTMLInputElement) document.createElement("input");
        numberInput.setType("number");
        numberInput.setAttribute("min", "0");

        HTMLElement result = document.createElement("p");
        result.setInnerHTML("Press check for result");

        HTMLButtonElement checkButton = (HTMLButtonElement) document.createElement("button");
        checkButton.setInnerHTML("Check");
        checkButton.onClick(evt -> {
            boolean flag = false;
            String reason = null;

            BigInteger bigNum = new BigInteger(numberInput.getValue());
            if(bigNum.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 || bigNum.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0) result.setInnerHTML("Number is either too large or is a negative number exceeding the signed 64-bit integer limit");
            else {
                long numToCheck = Long.parseLong(numberInput.getValue());

                if(numToCheck >= 2) {
                    for(long i = 2; i <= numToCheck / 2; i++)
                        if(numToCheck % i == 0) {
                            flag = true;
                            reason = String.format("%d * %d = %d", numToCheck / i, i, numToCheck);
                            break;
                        }

                    if(flag) result.setInnerHTML(String.format("Number is composite because %s", reason));
                    else result.setInnerHTML("Number is prime");
                }
                else result.setInnerHTML("Negative numbers, 0 and 1 are not valid numbers");
            }
        });

        document.getBody().appendChild(numberInput);
        document.getBody().appendChild(document.createElement("br"));
        document.getBody().appendChild(checkButton);
        document.getBody().appendChild(result);
    }
}
