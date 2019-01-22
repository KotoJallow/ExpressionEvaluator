run: eval testEval
	java TestExpressionEvaluator ExpressionEvaluator || true
testEval: TestExpressionEvaluator.java
	javac TestExpressionEvaluator.java
eval: ExpressionEvaluator.java
	javac ExpressionEvaluator.java
clean:
	rm *.class
