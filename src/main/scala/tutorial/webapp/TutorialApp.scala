package tutorial.webapp

import org.scalajs.dom
import dom.document
import org.scalajs.dom.html.Canvas

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel


object TutorialApp {
  def main(args: Array[String]): Unit = {

    addParagraph(createParagraph(), "Hello Word")
    addButton(createParagraph())
    val ctx = initScreen(createParagraph())

  }

  var playerX = 10
  var playerY = 300
  var playerSpeed = 6

  var ballX = 400
  var ballY = 180
  var playerSpeed2 = 6

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    addParagraph(document.body, "You clicked the button!")
  }

  def createParagraph(): dom.Node = {
    val p = document.createElement("p")
    document.body.appendChild(p)
    p
  }

  def addButton(targetNode: dom.Node) = {
    val button = document.createElement("button")
    button.innerHTML = "Click Me!"
    button.setAttribute("onClick", "addClickedMessage()")
    targetNode.appendChild(button)
    button
  }

  def addParagraph(targetNode: dom.Node, text: String): dom.Node = {
    val textNode = document.createTextNode(text)
    targetNode.appendChild(textNode)
  }

  val width = 600
  val height = 400

  def initScreen(targetNode: dom.Node): dom.CanvasRenderingContext2D = {
    val canvas = document.createElement("canvas").asInstanceOf[Canvas]
    canvas.width = width
    canvas.height = height

    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]


    document.createElement("p")
    targetNode.appendChild(canvas)


    def drawThings(): Unit = {
      ctx.fillStyle = "#008000"
      ctx.fillRect(0, 0, width, height)

      ctx.fillStyle = "#fff"
      ctx.fillRect(playerX, playerY, 10, 82)

      ctx.fillStyle = "#fff"
      ctx.beginPath()
      ctx.arc(ballX, ballY, 20, 0, Math.PI*2, true)
      ctx.fill()
    }

    def move: Unit = {
      ballX += 5

    }

    js.timers.setInterval(30) {
      move
      drawThings()

    }
    dom.window.addEventListener("keydown",(p: dom.KeyboardEvent) => {
      p.keyCode match {
        case 38 => playerY -= 4
        case 40 => playerY += 4

      }} ,false)

    ctx
    }

}