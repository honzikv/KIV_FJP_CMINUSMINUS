package parser.visitor;

import compiler.language.expression.BinaryOperationExpression;
import compiler.language.expression.Expression;
import compiler.language.expression.IdentifierExpression;
import compiler.language.expression.OperationType;
import compiler.language.expression.UnaryOperationExpression;
import compiler.language.expression.ValueExpression;
import compiler.language.variable.DataType;
import main.antlr4.grammar.CMMParser;

/**
 * Visitor pro expression
 */
public class ExpressionVisitor extends CMMLevelAwareVisitor<Expression> {

    public ExpressionVisitor(long depth) {
        super(depth);
    }

    @Override
    public Expression visitParenthesesExpression(CMMParser.ParenthesesExpressionContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public Expression visitParenthesizedExpression(CMMParser.ParenthesizedExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Expression visitMultiplicationExpression(CMMParser.MultiplicationExpressionContext ctx) {
        var expressionLeft = new ExpressionVisitor(depth).visit(ctx.expression(0));
        var expressionRight = new ExpressionVisitor(depth).visit(ctx.expression(1));
        var operation = OperationType.getBinaryOperationType(ctx.operation.getText());

        return new BinaryOperationExpression(expressionLeft, expressionRight,
                operation);
    }

    @Override
    public Expression visitAdditionExpression(CMMParser.AdditionExpressionContext ctx) {
        var expressionLeft = new ExpressionVisitor(depth).visit(ctx.expression(0));
        var expressionRight = new ExpressionVisitor(depth).visit(ctx.expression(1));
        var operation = OperationType.getBinaryOperationType(ctx.operation.getText());

        return new BinaryOperationExpression(expressionLeft, expressionRight,
                operation);
    }

    @Override
    public Expression visitComparisonExpression(CMMParser.ComparisonExpressionContext ctx) {
        var expressionLeft = new ExpressionVisitor(depth).visit(ctx.expression(0));
        var expressionRight = new ExpressionVisitor(depth).visit(ctx.expression(1));
        var operation = OperationType.getBinaryOperationType(ctx.operation.getText());

        return new BinaryOperationExpression(expressionLeft, expressionRight,
                operation);
    }

    @Override
    public Expression visitBooleanOperationExpression(CMMParser.BooleanOperationExpressionContext ctx) {
        var expressionLeft = new ExpressionVisitor(depth).visit(ctx.expression(0));
        var expressionRight = new ExpressionVisitor(depth).visit(ctx.expression(1));
        var operation = OperationType.getBinaryOperationType(ctx.operation.getText());

        return new BinaryOperationExpression(expressionLeft, expressionRight,
                operation);
    }

    @Override
    public Expression visitUnaryMinusExpression(CMMParser.UnaryMinusExpressionContext ctx) {
        var expressionLeft = new ExpressionVisitor(depth).visit(ctx.expression());
        var operation = OperationType.getUnaryOperationType(ctx.operation.getText());

        return new UnaryOperationExpression(expressionLeft, operation);
    }

    @Override
    public Expression visitNegationExpression(CMMParser.NegationExpressionContext ctx) {
        var expressionLeft = new ExpressionVisitor(depth).visit(ctx.expression());
        var operation = OperationType.getUnaryOperationType(ctx.NOT().getText());

        return new UnaryOperationExpression(expressionLeft, operation);
    }

    @Override
    public Expression visitValueExpression(CMMParser.ValueExpressionContext ctx) {
        var value = ctx.valueExpr().getText();
        return new ValueExpression(DataType.getDataTypeFromValue(value), value);
    }

    @Override
    public Expression visitIdentifierExpression(CMMParser.IdentifierExpressionContext ctx) {
        return new IdentifierExpression(ctx.IDENTIFIER().getText());
    }
}
