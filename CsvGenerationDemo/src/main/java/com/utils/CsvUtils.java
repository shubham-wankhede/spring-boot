package com.utils;

import com.entity.Student;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.List;

public class CsvUtils {

    public static StringWriter createCsv(List<Student> studentList) throws Exception{
        //where to want to write our data
        StringWriter stringWriter = new StringWriter();


        MappingStrategy columnStrategy =
                // new ColumnPositionMappingStrategy()
                // new HeaderColumnNameMappingStrategy();
                // new CustomMappingStrategyHardCodedValues();
                // new CustomMappingStrategyReflectionApiColumn();
                 new CustomMappingStrategy();
        columnStrategy.setType(Student.class);

        //bean to csv writer
        StatefulBeanToCsv csvBuilder =new StatefulBeanToCsvBuilder(stringWriter)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(columnStrategy)
                .build();

        csvBuilder.write(studentList);
        return stringWriter;
    }

    //harcoding the column names
    public static class CustomMappingStrategyHardCodedValues<T> extends ColumnPositionMappingStrategy<T> {
        @Override
        public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
            super.generateHeader(bean);
            return new String[]{"id","name","field","study_field"};
        }
    }

    //retrieving the column name through reflection api
    public static class CustomMappingStrategyReflectionApiColumn<T> extends ColumnPositionMappingStrategy<T> {
        @Override
        public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
            super.generateHeader(bean);
            Field[] fields = Student.class.getDeclaredFields();
            String[] customColumn = new String[fields.length];

            for(int i=0;i<fields.length;i++){
                customColumn[i]= fields[i].getName();
            }
            return customColumn;
        }
    }

    // to have both custom column order and custom column names
    public static class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T>{
        @Override
        public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
            final int numColumns = getFieldMap().values().size();
            super.generateHeader(bean);

            String[] customHeader = new String[numColumns];

            BeanField beanField;
            for(int i=0;i<numColumns;i++){
                beanField = findField(i);
                String columnHeaderName = extractHeaderName(beanField);
                customHeader[i]=columnHeaderName;

            }
            return customHeader;
        }
        public String extractHeaderName(BeanField beanField){
            if(beanField ==null || beanField.getField() ==null
                    || beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length==0){
                return StringUtils.EMPTY;
            }

            final CsvBindByName bindByNameAnnotation = beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class)[0];
            return bindByNameAnnotation.column();
        }
    }


    //  Simple CSV convertion
    public static StringWriter createSimpleCsv(List<Student> studentList) throws Exception{
        StringWriter stringWriter = new StringWriter();

        CSVWriter csvWriter = new CSVWriter(stringWriter,CSVWriter.DEFAULT_SEPARATOR
                ,CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.DEFAULT_ESCAPE_CHARACTER
                ,CSVWriter.DEFAULT_LINE_END);

        Field[] fields = Student.class.getDeclaredFields();
        String[] customColumn = new String[fields.length];

        for(int i=0;i<fields.length;i++){
            customColumn[i]= fields[i].getName();
        }

        csvWriter.writeNext(customColumn);

        for(Student student:studentList){
            csvWriter.writeNext(new String[]{
                    student.getId().toString(),
                    student.getName(),
                    student.getAge().toString(),
                    student.getStudyField()
            });
        }

        stringWriter.flush();

        return stringWriter;
    }
}
