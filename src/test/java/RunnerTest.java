import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;


import java.org.weizhou.solutions.core.LeetCodeSolution;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RunnerTest {

    @TestFactory
    public List<DynamicTest> generateTests() throws Exception {
        List<DynamicTest> tests = new ArrayList<DynamicTest>();
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("org.weizhou.solutions.core"))
                .setScanners(Scanners.SubTypes)
                .filterInputsBy(s -> s.endsWith(".class"))
        );
        // 自动发现所有LeetCodeSolution实现类
        Set<Class<? extends LeetCodeSolution>> subTypesOfLeetCodeSolutions = reflections.getSubTypesOf(LeetCodeSolution.class);
        for (Class<?> clazz : subTypesOfLeetCodeSolutions) {
            LeetCodeSolution LeetCodeSolutionInstance = (LeetCodeSolution) clazz.getDeclaredConstructor().newInstance();
            tests.add(DynamicTest.dynamicTest(
                    clazz.getSimpleName(),
                    () -> {
                        System.out.println("-- Testing:" + clazz.getName() + " --");
                        LeetCodeSolutionInstance.execute();
                    }
            ));
        }
        return tests;
    }
}
