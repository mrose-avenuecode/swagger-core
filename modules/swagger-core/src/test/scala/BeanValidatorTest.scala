
import models._
import models.composition.Pet;

import com.wordnik.swagger.util.Json
import com.wordnik.swagger.models._
import com.wordnik.swagger.models.properties._
import com.wordnik.swagger.converter._

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.Matchers


@RunWith(classOf[JUnitRunner])
class BeanValidatorTest extends FlatSpec with Matchers {
  it should "read bean validations" in {
    val schemas = ModelConverters.getInstance().readAll(classOf[BeanValidationsModel])
    val model = schemas.get("BeanValidationsModel")
    val properties = model.getProperties()

    val age = properties.get("age").asInstanceOf[IntegerProperty]
    age.getMinimum() should be (13.0)
    age.getMaximum() should be (99.0)

    val password = properties.get("password").asInstanceOf[StringProperty]
    password.getMinLength() should be (6)
    password.getMaxLength() should be (20)

    val minBalance = properties.get("minBalance").asInstanceOf[DoubleProperty]
    minBalance.getExclusiveMinimum().booleanValue() should be (true)

    val maxBalance = properties.get("maxBalance").asInstanceOf[DoubleProperty]
    maxBalance.getExclusiveMaximum().booleanValue() should be (true)
  }
}