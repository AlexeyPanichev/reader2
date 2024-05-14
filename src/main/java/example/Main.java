package example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = parseXML("C:\\Users\\Anna)))\\IdeaProjects\\reader2\\src\\main\\java\\example\\data.xml");

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static List<Employee> parseXML(String filePath) {
        List<Employee> employees = new ArrayList<>();

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("employee");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    Employee employee = new Employee();
                    employee.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    employee.setFirstName(eElement.getElementsByTagName("firstName").item(0).getTextContent());
                    employee.setLastName(eElement.getElementsByTagName("lastName").item(0).getTextContent());
                    employee.setCountry(eElement.getElementsByTagName("country").item(0).getTextContent());
                    employee.setAge(Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent()));

                    employees.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}