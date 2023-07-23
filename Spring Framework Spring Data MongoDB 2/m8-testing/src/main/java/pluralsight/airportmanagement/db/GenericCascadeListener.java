package pluralsight.airportmanagement.db;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class GenericCascadeListener extends AbstractMongoEventListener<Object> {
    private MongoTemplate mongoTemplate;

    public GenericCascadeListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent event) {
        Object document = event.getSource();
        ReflectionUtils.doWithFields(document.getClass(), docField -> {
            ReflectionUtils.makeAccessible(docField);

            if (docField.isAnnotationPresent(DBRef.class)) {
                final Object fieldValue = docField.get(document);

                // Save child
                this.mongoTemplate.save(fieldValue);
            }
        });
    }
}
