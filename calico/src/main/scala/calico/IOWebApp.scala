/*
 * Copyright 2022 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package calico

import calico.syntax.*
import calico.unsafe.given
import cats.effect.IO
import cats.effect.Resource
import org.scalajs.dom

trait IOWebApp:

  def rootElementId: String = "app"

  def render: Resource[IO, fs2.dom.HtmlElement[IO]]

  def main(args: Array[String]): Unit =
    val document = fs2.dom.Document[IO]
    val rootElement = document.getElementById(rootElementId).map(_.get)
    rootElement.flatMap(render.renderInto(_).useForever).unsafeRunAndForget()
