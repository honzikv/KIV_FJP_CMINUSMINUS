package compiler.language.statement;

import compiler.language.expression.Expression;

/**
 * Do While je to same jako while, jenom se bude jinak prekladat
 */
public class DoWhileStatement extends ForStatement {

    public DoWhileStatement(long depthLevel, Expression expression, BlockScope blockScope) {
        super(StatementType.DoWhileLoop ,depthLevel, expression, blockScope);
    }
}