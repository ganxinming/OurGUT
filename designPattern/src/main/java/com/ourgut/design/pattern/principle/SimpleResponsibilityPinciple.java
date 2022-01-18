package com.ourgut.design.pattern.principle;

/**
 * @author ganxinming
 * @createDate 2022/1/8
 * @description 单一职责（Simple Responsibility Pinciple，SRP）是指不要存在多于一个导致类变更的原因。
 * 假设我们有一个类负责两个职责，一旦发生需求变更，修改其中一个职责的逻辑代码，有可能导致另一个职责的功能发生故障。
 * 这样一来，这个类就存在两个导致类变更的原因。如何解决这个问题呢？将两个职责用两个类来实现，
 * 进行解耦。后期需求变更维护互不影响。这样的设计，可以降低类的复杂度，提高类的可读性，
 * 提高系统的可维护性，降低变更引起的风险。总体来说，就是一个类、接口或方法只负责一项职责。
 *
 *感悟：反正一个类只做一类事，难点在于怎么去划分，某些事属于一类，属于一类的事是不是有可能被分离出来
 * 尽量将类做到单一职责性
 */
public class SimpleResponsibilityPinciple {
}
