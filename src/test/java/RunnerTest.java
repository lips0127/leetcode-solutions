import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.reflections.Reflections;
import org.weizhou.solutions.core.Solution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RunnerTest {

    @TestFactory
    public List<DynamicTest> generateTests() throws Exception {
        List<DynamicTest> tests = new ArrayList<DynamicTest>();
        Reflections reflections = new Reflections("solutions");

        // 自动发现所有Solution实现类
        Set<Class<? extends Solution>> subTypesOfSolutions = reflections.getSubTypesOf(Solution.class);
        for (Class<?> clazz : subTypesOfSolutions) {
            Solution solutionInstance = (Solution) clazz.getDeclaredConstructor().newInstance();
            tests.add(DynamicTest.dynamicTest(
                    clazz.getSimpleName(),
                    () -> {
                        System.out.println("-- Testing:" + clazz.getName() + " --");
                        solutionInstance.execute();
                    }
            ));
        }
        return tests;
    }
}
