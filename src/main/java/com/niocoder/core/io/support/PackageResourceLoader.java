package com.niocoder.core.io.support;

import com.niocoder.core.io.FileSystemResource;
import com.niocoder.core.io.Resource;
import com.niocoder.util.Assert;
import com.niocoder.util.ClassUtils;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created on 2018/11/4.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
@Log
public class PackageResourceLoader {

    private final ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        Assert.notNull(classLoader, " ResourceLoader must not be null");
        this.classLoader = classLoader;
    }

    public Resource[] getResource(String basePackage) throws IOException {
        Assert.notNull(basePackage, "basepackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] result = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }

    protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
        if (!rootDir.exists()) {
            log.info("Skipping [" + rootDir.getAbsolutePath() + " ] because it not exist");
            return Collections.emptySet();
        }

        if (!rootDir.isDirectory()) {
            log.info("Skipping [" + rootDir.getAbsolutePath() + " ] because it a Directory");
        }

        if (!rootDir.canRead()) {
            log.info("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                    "] because the application is not allowed to read the directory");
            return Collections.emptySet();
        }
		/*String fullPattern = StringUtils.replace(rootDir.getAbsolutePath(), File.separator, "/");
		if (!pattern.startsWith("/")) {
			fullPattern += "/";
		}
		fullPattern = fullPattern + StringUtils.replace(pattern, File.separator, "/");
		*/
        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }


    protected void doRetrieveMatchingFiles(File dir, Set<File> result) throws IOException {

        File[] dirContents = dir.listFiles();
        if (dirContents == null) {
            log.info("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            return;
        }
        for (File content : dirContents) {

            if (content.isDirectory()) {
                if (!content.canRead()) {
                    log.info("Skipping subdirectory [" + dir.getAbsolutePath() +
                            "] because the application is not allowed to read the directory");
                } else {
                    doRetrieveMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }

        }
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
}
