import xml.etree.ElementTree as ET
import csv

entities = {'InvalidMoneyAmountException': 'main/java/exception/InvalidMoneyAmountException.xml',
            'Player': 'main/java/model/Player.xml',
            'PlayerTest': 'test/java/model/PlayerTest.xml'
            }

if __name__ == '__main__':

    for key, value in entities.items():
        xml_file_path = value

        # Read the XML content from the file
        with open(xml_file_path, 'r') as xml_file:
            xml_data = xml_file.read()

        # Parse the XML
        root = ET.fromstring(xml_data)

        # Prepare a CSV file for writing
        csv_file = open(f'code_elements_{key}.csv', 'w', newline='')
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(['Category', 'Element'])

        # Define categories and their corresponding XML tags
        categories = {
            'Identifiers': ['name'],
            'Method Signatures': ['constructor', 'function'],
            'Variable Names': ['name'],
            'Variable Types': ['type'],
            'Keywords': ['specifier', 'throws', 'if', 'else', 'return', 'throw', 'extends'],
            'Arguments': ['parameter', 'argument'],
            'Operators': ['operator']
        }

        # Iterate through categories and extract elements
        for category, tags in categories.items():
            for tag in tags:
                elements = root.findall('.//src:{tag}'.format(tag=tag),
                                        namespaces={'src': 'http://www.srcML.org/srcML/src'})
                for element in elements:
                    text = ' '.join(element.itertext()).strip()
                    csv_writer.writerow([category, text])

        # Close the CSV file
        csv_file.close()

        print('CSV file generated successfully.')

