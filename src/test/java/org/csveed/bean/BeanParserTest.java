package org.csveed.bean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.csveed.common.Column;
import org.csveed.report.CsvException;
import org.csveed.test.model.BeanLotsOfIgnores;
import org.csveed.test.model.BeanWithWrongAnnotation;
import org.csveed.test.model.BeanWithoutGettersAndSetters;
import org.junit.jupiter.api.Test;

public class BeanParserTest {

    @Test
    public void noGettersAndSetters() {
        BeanParser beanParser = new BeanParser();
        BeanInstructions instructions = beanParser.getBeanInstructions(BeanWithoutGettersAndSetters.class);
        assertNull(instructions.getProperties().fromName(new Column("a")));
    }

    @Test
    public void caseInsensitivity() {
        BeanParser beanParser = new BeanParser();
        BeanInstructions instructions = beanParser.getBeanInstructions(BeanLotsOfIgnores.class);
        assertNotNull(instructions.getProperties().fromName(new Column("takeThis1")));
        assertNotNull(instructions.getProperties().fromName(new Column("takethis1")));
    }

    @Test
    public void wrongAnnotation() {
        BeanParser beanParser = new BeanParser();
        assertThrows(CsvException.class, () ->  {
            beanParser.getBeanInstructions(BeanWithWrongAnnotation.class);
        });
    }

}
