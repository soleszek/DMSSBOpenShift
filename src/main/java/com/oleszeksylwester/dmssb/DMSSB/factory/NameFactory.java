package com.oleszeksylwester.dmssb.DMSSB.factory;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NameFactory {

    public String createName(Long id, String type) {

        int year = LocalDate.now().getYear();
        String yearStr = String.valueOf(year);

        int numberOfDigits = 6;

        String name = "";

        String idString = String.valueOf(id);

        int length = idString.length();

        switch (type) {

            case "user": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }
                return name = "U-" + yearStr + "-" + name + idString;
            }

            case "drawing": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }

                return name = "drw-" + yearStr + "-" + name + idString;
            }

            case "image": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }

                return name = "img-" + yearStr + "-" + name + idString;
            }

            case "document": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }

                return name = "doc-" + yearStr + "-" + name + idString;
            }

            case "route": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }

                return name = "R-" + yearStr + "-" + name + idString;
            }

            case "task": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }

                return name = "T-" + yearStr + "-" + name + idString;
            }

            case "message": {

                for (int i = 0; i < (numberOfDigits - length); i++) {
                    name = name + "0";
                }

                return name = "M-" + yearStr + "-" + name + idString;
            }
        }

        return null;
    }
}
