//**
//**  ClassMethodVisitor.java
//**  Supercraft
//**  (c) voidzm 2013 **//

package com.voidzm.supercraft.asm;

import static org.objectweb.asm.Opcodes.ASM4;

import java.lang.reflect.Constructor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class ClassMethodVisitor extends ClassVisitor {

	private String methodToVisit;
	private Class<? extends MethodVisitor> visitorClass;
	
	public ClassMethodVisitor(ClassVisitor cv, String method, Class<? extends MethodVisitor> mvClass) {
		super(ASM4, cv);
		methodToVisit = method;
		visitorClass = mvClass;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		if(!name.equals(this.methodToVisit)) return super.visitMethod(access, name, desc, signature, exceptions);
		else {
			Constructor<? extends MethodVisitor> visitorConstructor = null;
			try {
				visitorConstructor = visitorClass.getConstructor(MethodVisitor.class);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			MethodVisitor target = null;
			try {
				target = visitorConstructor.newInstance(super.visitMethod(access, name, desc, signature, exceptions));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return target;
		}
	}

}
