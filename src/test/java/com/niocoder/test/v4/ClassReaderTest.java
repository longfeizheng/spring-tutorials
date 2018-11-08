package com.niocoder.test.v4;

import com.niocoder.core.annotation.AnnotationAttributes;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.niocoder.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

/**
 * Created on 2018/11/4.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws Exception {
        ClassPathResource resource = new ClassPathResource("com/niocoder/service/v4/NioCoderService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visiter = new ClassMetadataReadingVisitor();
        reader.accept(visiter, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visiter.isAbstract());
        Assert.assertFalse(visiter.isInterface());
        Assert.assertFalse(visiter.isFinal());
        Assert.assertEquals("com.niocoder.service.v4.NioCoderService", visiter.getClassName());
        Assert.assertEquals("java.lang.Object", visiter.getSuperClassName());
        Assert.assertEquals(0, visiter.getInterfaces().length);

    }

    @Test
    public void testGetAnnoation() throws Exception {
        ClassPathResource resource = new ClassPathResource("com/niocoder/service/v4/NioCoderService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor,ClassReader.SKIP_DEBUG);

        String annotation = "com.niocoder.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        Assert.assertEquals("nioCoder",attributes.get("value"));
    }
}
