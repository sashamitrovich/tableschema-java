package io.frictionlessdata.tableschema.field;

import io.frictionlessdata.tableschema.exception.ConstraintsException;
import io.frictionlessdata.tableschema.exception.InvalidCastException;
import io.frictionlessdata.tableschema.exception.TypeInferringException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import java.net.URI;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearmonthField extends Field<DateTime> {
    // yyyy-MM
    private static final String REGEX_YEARMONTH = "([0-9]{4})-(1[0-2]|0[1-9])";

    YearmonthField() {
        super();
    }

    public YearmonthField(String name) {
        super(name, FIELD_TYPE_YEARMONTH);
    }

    public YearmonthField(String name, String format, String title, String description,
                          URI rdfType, Map constraints, Map options) {
        super(name, FIELD_TYPE_YEARMONTH, format, title, description, rdfType, constraints, options);
    }

    @Override
    public DateTime parseValue(String value, String format, Map<String, Object> options) throws InvalidCastException, ConstraintsException {
        Pattern pattern = Pattern.compile(REGEX_YEARMONTH);
        Matcher matcher = pattern.matcher(value);

        if(matcher.matches()){
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM");
            DateTime dt = formatter.parseDateTime(value);

            return dt;

        }else{
            throw new TypeInferringException();
        }
    }

    @Override
    public String parseFormat(String value, Map<String, Object> options) {
        return "default";
    }
}