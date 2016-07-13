package org.kotlinnative.translator.llvm.types

import org.kotlinnative.translator.exceptions.UnimplementedException
import org.kotlinnative.translator.llvm.LLVMExpression
import org.kotlinnative.translator.llvm.LLVMSingleValue

abstract class LLVMType() : Cloneable {

    open fun operatorPlus(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorTimes(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorMinus(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorLt(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorGt(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorLeq(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorGeq(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorEq(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun operatorNeq(firstOp: LLVMSingleValue, secondOp: LLVMSingleValue): LLVMExpression = throw UnimplementedException()
    open fun parseArg(inputArg: String) = inputArg

    fun makeClone() = clone()

    abstract val align: Int
    abstract val size: Byte
}

fun parseLLVMType(type: String): LLVMType = when (type) {
    "i32" -> LLVMIntType()
    "i16" -> LLVMShortType()
    "i8" -> LLVMCharType()
    "i1" -> LLVMBooleanType()
    "double" -> LLVMDoubleType()
    "Unit" -> LLVMVoidType()
    else -> LLVMReferenceType(type)
}
